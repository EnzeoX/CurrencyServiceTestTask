package com.github.main.utils;

import com.github.main.entity.EntityXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLCustomDecoder {

    public List<EntityXml> getEntityFromXML(String data) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<EntityXml> listOfEntities = new ArrayList<>();
        EntityXml entity = null;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(new InputSource(new StringReader(data)));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("currency");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    entity = new EntityXml(
                            element.getElementsByTagName("r030").item(0).getTextContent(),
                            element.getElementsByTagName("txt").item(0).getTextContent(),
                            element.getElementsByTagName("rate").item(0).getTextContent(),
                            element.getElementsByTagName("cc").item(0).getTextContent(),
                            element.getElementsByTagName("exchangedate").item(0).getTextContent()
                    );
                }
                listOfEntities.add(entity);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return listOfEntities;
    }
}
