package lapr.project.utils;

import org.w3c.dom.Node;

import javax.xml.parsers.ParserConfigurationException;

/**
 * All domains classes should include this interface.
 * @param <T>
 */
@FunctionalInterface
public interface Importable<T extends Exportable> {
	/**
	 * Imports the object content from an XML format.
	 *
     * @param node
	 * @return Structured String containing content.
     * @throws javax.xml.parsers.ParserConfigurationException
	 */
	T importContentFromXMLNode(Node node) throws ParserConfigurationException;
}
