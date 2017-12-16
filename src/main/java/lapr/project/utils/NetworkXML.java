package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Section;
import lapr.project.model.Section.Direction;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;

public class NetworkXML implements FileFormat {

    private File file;
    private XMLStreamReader reader;
    private String elementContent;

    private Project m_project;
    private String m_network_id;
    private String m_network_description;
    private Road m_road;
    private int m_toll_class;
    private Section m_section;
    private Junction m_begin;
    private Junction m_end;
    private List<Segment> m_listOfSegment;
    private Segment m_segment;

    public NetworkXML() {
        //Empty constructor
    }

    @Override
    public List<Vehicle> importVehicles(File file) throws FileNotFoundException, ImportException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean importNetwork(Project project, File file) throws FileNotFoundException, ImportException {
        if (project == null || file == null || !file.exists()) {
            throw new FileNotFoundException("Must be an existing file.");
        }
        this.file = file;
        m_project = project;
        try {
            this.importXML();
        } catch (XMLStreamException ex) {
            //throw new ImportException(ex);
            return false;
        }
        return true;
    }

    private void importXML() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        this.reader = factory.createXMLStreamReader(new FileInputStream(this.file));
        this.elementContent = null;
        while (this.reader.hasNext()) {
            int xmlEvent = this.reader.next();
            identifyXML(xmlEvent);
        }
    }

    private void identifyXML(int xmlEvent) throws XMLStreamException {
        switch (xmlEvent) {
            case XMLStreamConstants.START_ELEMENT: {
                switch (this.reader.getLocalName()) {
                    case "Network": {
                        m_network_id = reader.getAttributeValue(null, "id");
                        m_network_description = reader.getAttributeValue(null, "description");
                        readNetworkElements();
                        break;
                    }
                    default:
                        break;
                }

            }
        }
    }

    private String getElementText() {
        return this.reader.getText().trim();
    }

    private void readNetworkElements() throws XMLStreamException {
        while (this.reader.hasNext()) {
            int xmlEvent = this.reader.next();
            checkNetworkElements(xmlEvent);
        }

    }

    private void checkNetworkElements(int xmlEvent) throws XMLStreamException {
        switch (xmlEvent) {
            case XMLStreamConstants.START_ELEMENT: {
                checkNetworkStartElement();
                break;
            }
            case XMLStreamConstants.CHARACTERS: {
                this.elementContent = getElementText();
                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                checkNetworkEndElement();
                if (this.elementContent != null) {
                    this.elementContent = null;
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    private void checkNetworkStartElement() {
        switch (reader.getLocalName()) {
            case "node_list": {
                break;
            }
            case "node": {
                String junctionId = this.reader.getAttributeValue(null, "id");
                Junction j = new Junction(junctionId);
                if (j.validate()) {
                    m_project.addJunction(j);
                }
                break;
            }

            case "road_list": {
                break;
            }

            case "road": {
                String id = this.reader.getAttributeValue(null, "id");
                m_road = new Road();
                m_road.setRoadID(id);
                break;
            }

            case "road_name": {
                break;
            }

            case "typology": {
                break;
            }

            case "toll_fare": {
                break;
            }

            case "class": {
                m_toll_class = Integer.parseInt(this.reader.getAttributeValue(null, "id"));
                break;
            }

            case "section_list": {
                break;
            }
            case "road_section": {
                m_section = new Section();
                m_begin = m_project.getJunction(this.reader.getAttributeValue(null, "begin"));
                m_end = m_project.getJunction(this.reader.getAttributeValue(null, "end"));
                m_section.setBeginJunction(m_begin);
                m_section.setEndJunction(m_end);
                break;
            }
            case "road_id": {
                break;
            }

            case "direction": {
                break;
            }

            case "segment_list": {
                m_listOfSegment = new ArrayList<>();
                break;
            }

            case "segment": {
                m_segment = new Segment();
                m_segment.setM_segment_index(Integer.parseInt(this.reader.getAttributeValue(null, "id")));
                break;
            }
            default: {
                break;
            }
        }
    }

    private void checkNetworkEndElement() {
        switch (reader.getLocalName()) {
            case "node_list": {
                break;
            }
            case "node": {
                break;
            }
            case "road_list": {
                break;
            }

            case "road": {
                if (m_road.validate()) {
                    m_project.addRoad(m_road);
                }
                m_road = null;
                break;
            }

            case "road_name": {
                m_road.setName(this.elementContent);
                break;
            }

            case "typology": {
                m_road.setTypology(this.elementContent);
                break;
            }

            case "toll_fare": {
                break;
            }

            case "class": {
                double value = Double.parseDouble(elementContent);
                m_road.addTollFare(m_toll_class, value);
                m_toll_class = -1;
                break;
            }

            case "section_list": {
                break;
            }
            case "road_section": {
                if (m_section.validate()) {
                    m_project.addSection(m_section);
                }
                break;
            }
            case "road_id": {
                m_section.setRoadID(this.elementContent);
                Road r = m_project.getRoadByRoadID(m_section.getRoadID());
                m_section.setTypology(r.getTypology());
                break;
            }
            case "direction": {
                Direction d = null;
                this.elementContent = this.elementContent.toUpperCase();
                switch (this.elementContent) {
                    case "DIRECT":
                        d = Direction.DIRECT;
                        break;
                    case "REVERSE":
                        d = Direction.REVERSE;
                        break;
                    case "BIDIRECTIONAL":
                        d = Direction.BIDIRECTIONAL;
                        break;
                    default:
                        d = Direction.DIRECT;
                        break;
                }
                m_section.setDirection(d);
                break;
            }
            case "segment_list": {
                m_section.setSegmentList(m_listOfSegment);
                m_listOfSegment = null;
                break;
            }
            case "segment": {
                m_listOfSegment.add(m_segment);
                //segment.setM_slope();
                m_segment = null;
                break;
            }

            case "init_height": {
                m_segment.setM_initial_height(Double.parseDouble(this.elementContent));
                break;
            }
            case "final_height": {
                m_segment.setM_final_height(Double.parseDouble(this.elementContent));
                break;
            }
            case "length": {
                m_segment.setM_length(Double.parseDouble(this.elementContent.split("")[0]));
                break;
            }
            case "max_velocity": {
                Double max_vel = Double.parseDouble(this.elementContent.split("")[0]);
                m_segment.setM_maximum_velocity(max_vel);
                break;
            }
            case "min_velocity": {
                Double min_vel = Double.parseDouble(this.elementContent.split("")[0]);
                m_segment.setM_maximum_velocity(min_vel);
                break;
            }
            case "wind_direction": {
                m_segment.setM_wind_direction(Double.parseDouble(this.elementContent));
                break;
            }
            case "wind_speed": {
                Double wind_speed = Double.parseDouble(this.elementContent.split("")[0]);
                m_segment.setWindSpeed(wind_speed);
                break;
            }
            default:
                break;
        }
    }
}
