package com.endava.user.management.web.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.endava.user.management.web.mapping.ControllerMapping;
import com.endava.user.management.web.mapping.HandlerMappingRegistry;
import com.endava.user.management.web.mapping.RequestMapping;

public class XmlHandlerMappingReader {

	private final Document document;

	public XmlHandlerMappingReader(String xmlFilePath) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(getClass().getResourceAsStream(xmlFilePath));
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public HandlerMappingRegistry parseMappingRegistry() {
		HandlerMappingRegistry registry = new HandlerMappingRegistry();
		NodeList nodes = document.getElementsByTagName("controller");
		for (int i = 0; i < nodes.getLength(); ++i) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element  = (Element) node;
				String className = element.getAttribute("class");
				ControllerMapping mapping = new ControllerMapping(className);
				parseControllerRequestMappings(mapping, element);
				registry.addControllerMapping(mapping);
			}
		}
		return registry;
	}

	private void parseControllerRequestMappings(ControllerMapping mapping, Element controllerElement) {
		NodeList nodes = controllerElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); ++i) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String methodName = element.getAttribute("method");
				String urlPattern = getUrlPattern(element.getChildNodes());
				mapping.addRequestMapping(new RequestMapping(methodName, urlPattern));
			}
		}
	}

	private String getUrlPattern(NodeList mappingElement) {
		for (int i = 0; i < mappingElement.getLength(); ++i) {
			Node node = mappingElement.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				return e.getTextContent();
			}
		}
		return "";
	}
}
