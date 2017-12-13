package lapr.project.utils;

import org.w3c.dom.Node;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Interface that allows object's content to be exported.
 * All domains classes should include this interface.
 */
@FunctionalInterface
public interface Exportable {

	/**
	 * Exports the object content to a string format.
	 *
	 * @return Structured String containing content.
     * @throws javax.xml.transform.TransformerException
     * @throws javax.xml.parsers.ParserConfigurationException
	 */
	default String exportContentToString() throws TransformerException, ParserConfigurationException {
		String content = "";

		Node node = exportContentToXMLNode();

		XMLParser xmlParser = new XMLParser();

		//It exports only the element representation to XML, ommiting the XML header
		content = xmlParser.convertToString(node);

		return content;
	}

	/**
	 * Exports the object content to a string format.
	 *
	 * @return Structured String containing content.
     * @throws javax.xml.parsers.ParserConfigurationException
	 */
	Node exportContentToXMLNode() throws ParserConfigurationException;
}
