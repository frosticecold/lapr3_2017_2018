package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.Project;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLReader {

    private String filepathXML = "";

    /**
     *
     * @param fileXML
     */
    public XMLReader(String fileXML) {
        if (testFilepath(fileXML)) {
            filepathXML = fileXML;
        }
    }

    public Project readValuesFromXML(Project project) throws ParseException {
        try {

            File xmlFile = new File(filepathXML);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new FileReader(xmlFile));
            
            Document document = documentBuilder.parse(inputSource);

        } catch (FileNotFoundException e) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Ficheiro nÃ£o encontrado");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, e);
        }
        return project;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public boolean testFilepath(String filepath) {
        File file = new File(filepath);
        if (!file.exists() || (file.exists() && file.isDirectory())) {
            System.out.println("File not found. Using default file");
            return false;
        }
        return true;
    }

}
