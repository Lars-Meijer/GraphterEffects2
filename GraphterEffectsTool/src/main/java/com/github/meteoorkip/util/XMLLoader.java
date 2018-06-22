package com.github.meteoorkip.util;


import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XMLLoader {

    /**
     * DocumentBuilder object that parses text into SAX Documents.
     */
    private DocumentBuilder dBuilder = null;

    /**
     * Creates a new XMLLoader, and sets it up.
     */
    public XMLLoader() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            this.dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            //This shouldn't happen, since we do not change configuration.
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads a String containing XML data into a Tree.
     *
     * @param xmlDoc String containing XML data.
     * @return The created Tree.
     * @throws SAXException Thrown when the XML document is malformed.
     */
    public Tree readXML(String xmlDoc) throws SAXException {
        try {
            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlDoc.getBytes("utf-8"))));
            return readRec(doc.getDocumentElement());
        } catch (IOException e) {
            //We do not use the functionality to read from a file, so this should never happen.
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }


    /**
     * Converts a DOM node into a Tree, recursively.
     *
     * @param elem The DOM node to convert into a tree.
     * @return The Tree.
     */
    private Tree readRec(Node elem) {
        Tree tree = new Tree();
        tree.setName(elem.getNodeName());
        NodeList children = elem.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (!(children.item(i) instanceof DeferredTextImpl)) {
                tree.addChild(readRec(children.item(i)));
            }
        }
        return tree;
    }
}
