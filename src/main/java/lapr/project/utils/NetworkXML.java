package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.Junction;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.utils.graphbase.Graph;

public class NetworkXML implements FileFormat {

    private File file;
    private XMLStreamReader reader;
    private String elementContent;

    private Graph<Junction, Section> graph;
    private String network_id;
    private String network_description;

    public NetworkXML() {

    }

    @Override
    public List<Vehicle> importVehicles(File file) throws FileNotFoundException, ImportException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Graph<Junction, Section> importNetwork(File file) throws FileNotFoundException, ImportException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException("Must be an existing file.");
        }
        this.file = file;

        try {
            this.importXML();
        } catch (XMLStreamException ex) {
            throw new ImportException(ex);
        }
        return this.graph;
    }

    private void importXML() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        this.reader = factory.createXMLStreamReader(new FileInputStream(this.file));
        this.elementContent = null;
        while (this.reader.hasNext()) {
            int xmlEvent = this.reader.next();
            identifyXml(xmlEvent);
        }
    }

    private void identifyXml(int xmlEvent) throws XMLStreamException {
        switch (xmlEvent) {
            case XMLStreamConstants.START_ELEMENT: {
                switch (this.reader.getLocalName()) {
                    case "Network": {
                        network_id = reader.getAttributeValue(null, "id");
                        network_description = reader.getAttributeValue(null, "description");
                        break;
                    }
                    case "node_list": {
                        this.graph = new Graph<>(true);
                        readNodeElements();

                    }
                }
            }
        }
    }

    private void readNodeElements() throws XMLStreamException {
        while (this.reader.hasNext()) {
            int xmlEvent = this.reader.next();
            if (xmlEvent == XMLStreamConstants.END_ELEMENT && this.reader.getLocalName().equals("node_list")) {
                break;
            }
            checkNodeElements(xmlEvent);
        }
    }

    private void checkNodeElements(int xmlEvent) {
        switch (xmlEvent) {
            case XMLStreamConstants.START_ELEMENT: {
                checkNodeStartElement();
                break;
            }
            case XMLStreamConstants.CHARACTERS: {
                this.elementContent = this.reader.getText().trim();
                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                checkNodeEndElement();
                if (this.elementContent != null) {
                    this.elementContent = null;
                }
                break;
            }
        }
    }

    private void checkNodeStartElement() {
        switch (reader.getLocalName()) {
            case "node": {
                String name = reader.getAttributeValue(null, "id");
                break;
            }
        }
    }

    private void checkNodeEndElement() {
        switch (reader.getLocalName()) {
            case "vehicle": {
                //addVehicleToList();
                break;
            }
        }
    }

}
