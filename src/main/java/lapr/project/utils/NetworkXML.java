package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Section.Direction;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.utils.graphbase.Graph;

public class NetworkXML implements FileFormat {

    private File file;
    private XMLStreamReader reader;
    private String elementContent;

    private Project project;
    private String m_network_id;
    private String m_network_description;
    private Road m_road;
    private int m_toll_class;
    private Section m_section;
    private Junction m_begin;
    private Junction m_end;
    private List<Segment> m_listOfSegment;
    private Segment m_segment;
    private boolean m_readingtollfare = false;
    private boolean m_readingtoll = false;

    //Tags
    private static final String NETWORK_TAG = "Network";
    private static final String NODE_LIST_TAG = "node_list";
    private static final String NODE_TAG = "node";
    private static final String ROADLIST_TAG = "road_list";
    private static final String ROAD_TAG = "road";
    private static final String ROAD_NAME_TAG = "road_name";
    private static final String TYPOLOGY_TAG = "typology";
    private static final String TOLL_FARE_TAG = "toll_fare";
    private static final String TOLL_TAG = "toll";
    private static final String CLASS_TAG = "class";
    private static final String SECTION_LIST_TAG = "section_list";
    private static final String ROAD_SECTION_TAG = "road_section";
    private static final String ROAD_ID_TAG = "road_id";
    private static final String DIRECTION_TAG = "direction";
    private static final String SEGMENT_LIST_TAG = "segment_list";
    private static final String SEGMENT_TAG = "segment";
    private static final String INITIAL_HEIGHT_TAG = "init_height";
    private static final String FINAL_HEIGHT_TAG = "final_height";
    private static final String LENGTH_TAG = "length";
    private static final String MAX_VELOCITY_TAG = "max_velocity";
    private static final String MIN_VELOCITY_TAG = "min_velocity";
    private static final String WIND_DIRECTION_TAG = "wind_direction";
    private static final String WIND_SPEED_TAG = "wind_speed";

    public NetworkXML() {
        //Empty constructor
    }

    @Override
    public List<Vehicle> importVehicles(File file) throws FileNotFoundException, ImportException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pair<Graph<Junction, Section>, List<Road>> importNetwork(File file) throws FileNotFoundException, ImportException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException("Must be an existing file.");
        }
        this.file = file;
        project = new Project();
        try {
            this.importXML();
        } catch (XMLStreamException ex) {
            throw new ImportException(ex);
        }
        Pair<Graph<Junction, Section>, List<Road>> pair = new Pair<>();
        pair.setFirstElement(project.getRoadNetwork());
        pair.setSecondElement(project.getListRoads());
        return pair;
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
                    case NETWORK_TAG: {
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
            case NODE_LIST_TAG: {
                break;
            }
            case NODE_TAG: {
                String junctionId = this.reader.getAttributeValue(null, "id");
                Junction j = new Junction(junctionId);
                if (j.validate()) {
                    project.addJunction(j);
                }
                break;
            }

            case ROADLIST_TAG: {
                break;
            }

            case ROAD_TAG: {
                String id = this.reader.getAttributeValue(null, "id");
                m_road = new Road();
                m_road.setRoadID(id);
                break;
            }

            case ROAD_NAME_TAG: {
                break;
            }

            case TYPOLOGY_TAG: {
                break;
            }

            case TOLL_FARE_TAG: {
                m_readingtollfare = true;
                break;
            }

            case TOLL_TAG: {
                m_readingtoll = true;
                break;
            }

            case CLASS_TAG: {
                m_toll_class = Integer.parseInt(this.reader.getAttributeValue(null, "id"));
                break;
            }

            case SECTION_LIST_TAG: {
                break;
            }
            case ROAD_SECTION_TAG: {
                m_section = new Section();
                m_begin = project.getJunction(this.reader.getAttributeValue(null, "begin"));
                m_end = project.getJunction(this.reader.getAttributeValue(null, "end"));
                m_section.setBeginJunction(m_begin);
                m_section.setEndJunction(m_end);
                break;
            }
            case ROAD_ID_TAG: {
                break;
            }

            case DIRECTION_TAG: {
                break;
            }

            case SEGMENT_LIST_TAG: {
                m_listOfSegment = new ArrayList<>();
                break;
            }

            case SEGMENT_TAG: {
                m_segment = new Segment();
                m_segment.setSegmentIndex(Integer.parseInt(this.reader.getAttributeValue(null, "id")));
                break;
            }
            default: {
                break;
            }
        }
    }

    private void checkNetworkEndElement() {
        switch (reader.getLocalName()) {
            case NODE_LIST_TAG: {
                break;
            }
            case NODE_TAG: {
                break;
            }
            case ROADLIST_TAG: {
                break;
            }

            case ROAD_TAG: {
                if (m_road.validate()) {
                    project.addRoad(m_road);
                }
                m_road = null;
                break;
            }

            case ROAD_NAME_TAG: {
                m_road.setName(this.elementContent);
                break;
            }

            case TYPOLOGY_TAG: {
                m_road.setTypology(this.elementContent);
                break;
            }

            case TOLL_FARE_TAG: {
                if (m_readingtollfare) {
                    m_readingtollfare = false;
                }
                break;
            }
            case TOLL_TAG: {
                if (m_readingtoll) {
                    m_readingtoll = false;
                }
                break;
            }

            case CLASS_TAG: {
                double value = Double.parseDouble(elementContent);
                if (m_readingtollfare) {
                    m_road.addTollFare(m_toll_class, value);
                    m_toll_class = -1;
                }
                if (m_readingtoll) {
                    m_section.addToll(m_toll_class, value);
                    m_toll_class = -1;
                }
                break;
            }

            case SECTION_LIST_TAG: {
                break;
            }
            case ROAD_SECTION_TAG: {
                if (m_section.validate()) {
                    project.addSection(m_section);
                }
                break;
            }
            case ROAD_ID_TAG: {
                String road_id = this.elementContent.trim().replaceAll("\"", "");
                m_section.setRoadID(road_id);
                Road r = project.getRoadByRoadID(m_section.getRoadID());
                m_section.setTypology(r.getTypology());
                break;
            }
            case DIRECTION_TAG: {
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
            case SEGMENT_LIST_TAG: {
                m_section.setSegmentList(m_listOfSegment);
                m_listOfSegment = null;
                break;
            }
            case SEGMENT_TAG: {
                m_listOfSegment.add(m_segment);
                m_segment = null;
                break;
            }

            case INITIAL_HEIGHT_TAG: {
                m_segment.setInitialHeight(Double.parseDouble(this.elementContent));
                break;
            }
            case FINAL_HEIGHT_TAG: {
                m_segment.setFinalHeight(Double.parseDouble(this.elementContent));
                break;
            }
            case LENGTH_TAG: {
                m_segment.setLength(Double.parseDouble(this.elementContent.split(" ")[0]));
                break;
            }
            case MAX_VELOCITY_TAG: {
                double max_vel = Double.parseDouble(this.elementContent.split(" ")[0]);
                m_segment.setMaximumVelocity(max_vel);
                break;
            }
            case MIN_VELOCITY_TAG: {
                Double min_vel = Double.parseDouble(this.elementContent.split(" ")[0]);
                m_segment.setMinimumVelocity(min_vel);
                break;
            }
            case WIND_DIRECTION_TAG: {
                m_segment.setWindDirection(Double.parseDouble(this.elementContent));
                break;
            }
            case WIND_SPEED_TAG: {
                Double wind_speed = Double.parseDouble(this.elementContent.split(" ")[0]);
                m_segment.setWindSpeed(wind_speed);
                break;
            }
            default:
                break;
        }
    }
}
