package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.Junction;
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

    private RoadNetwork roadnetwork;
    private String network_id;
    private String network_description;
    private Map<Road, String> mapOfRoads;
    private Road r;
    private Map<Integer, Double> mapOfTolls;
    private int toll_class;
    private Section section;
    private Junction begin;
    private Junction end;
    private List<Segment> listOfSegment;
    private Segment segment;

    public NetworkXML() {

    }

    @Override
    public List<Vehicle> importVehicles(File file) throws FileNotFoundException, ImportException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadNetwork importNetwork(File file) throws FileNotFoundException, ImportException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException("Must be an existing file.");
        }
        this.file = file;

        try {
            this.importXML();
        } catch (XMLStreamException ex) {
            throw new ImportException(ex);
        }
        return this.roadnetwork;
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
                        network_id = reader.getAttributeValue(null, "id");
                        network_description = reader.getAttributeValue(null, "description");
                        this.roadnetwork = new RoadNetwork(network_id, network_description);
                        readNetworkElements();
                        break;
                    }
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
                this.roadnetwork.addJunction(j);
                break;
            }

            case "road_list": {
                mapOfRoads = new HashMap<>();
                break;
            }

            case "road": {
                String id = this.reader.getAttributeValue(null, "id");
                r = new Road();
                r.setRoadID(id);
                break;
            }

            case "road_name": {
                break;
            }

            case "typology": {
                break;
            }

            case "toll_fare": {
                mapOfTolls = new HashMap<>();
                break;
            }

            case "class": {
                toll_class = Integer.parseInt(this.reader.getAttributeValue(null, "id"));
                break;
            }

            case "section_list": {
                break;
            }
            case "road_section": {
                section = new Section();
                begin = roadnetwork.getJunction(this.reader.getAttributeValue(null, "begin"));
                end = roadnetwork.getJunction(this.reader.getAttributeValue(null, "end"));
                section.setBeginJunction(begin);
                section.setEndJunction(end);
                break;
            }
            case "road_id": {
                break;
            }

            case "direction": {
                break;
            }

            case "segment_list": {
                listOfSegment = new ArrayList<>();
                break;
            }

            case "segment": {
                segment = new Segment();
                segment.setM_segment_index(Integer.parseInt(this.reader.getAttributeValue(null, "id")));
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
                roadnetwork.addRoad(r);
                r = null;
                break;
            }

            case "road_name": {
                r.setName(this.elementContent);
                break;
            }

            case "typology": {
                r.setTypology(this.elementContent);
                mapOfRoads.put(r, this.elementContent);
                break;
            }

            case "toll_fare": {
                r.setTollFare(mapOfTolls);
                break;
            }

            case "class": {
                mapOfTolls.put(toll_class, Double.parseDouble(this.elementContent));
                break;
            }

            case "section_list": {
                break;
            }
            case "road_section": {
                roadnetwork.addSection(section);
                break;
            }
            case "road_id": {
                section.setRoadID(this.elementContent);
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
                }
                if (d == null) {
                    d = Direction.BIDIRECTIONAL;
                }
                section.setDirection(d);
                break;
            }
            case "segment_list": {
                section.setSegmentList(listOfSegment);
                listOfSegment = null;
                break;
            }
            case "segment": {
                listOfSegment.add(segment);
                //segment.setM_slope();
                segment = null;
                break;
            }

            case "init_height": {
                segment.setM_initial_height(Double.parseDouble(this.elementContent));
                break;
            }
            case "final_height": {
                segment.setM_final_height(Double.parseDouble(this.elementContent));
                break;
            }
            case "length": {
                segment.setM_length(Double.parseDouble(this.elementContent.split("")[0]));
                break;
            }
            case "max_velocity": {
                Double max_vel = Double.parseDouble(this.elementContent.split("")[0]);
                segment.setM_maximum_velocity(max_vel);
                break;
            }
            case "min_velocity": {
                Double min_vel = Double.parseDouble(this.elementContent.split("")[0]);
                segment.setM_maximum_velocity(min_vel);
                break;
            }
            case "wind_direction": {
                segment.setM_wind_direction(Double.parseDouble(this.elementContent));
                break;
            }
            case "wind_speed": {
                Double wind_speed = Double.parseDouble(this.elementContent.split("")[0]);
                segment.setM_wind_speed(wind_speed);
                break;
            }
        }
    }
}
