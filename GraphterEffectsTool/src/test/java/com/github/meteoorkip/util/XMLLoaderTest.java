package com.github.meteoorkip.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import testutils.TestUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Tests functionality of XMLLoader
 */
public class XMLLoaderTest {


    /**
     * XML test file containing an xml root and nothing else.
     */
    private String only_root = null;
    /**
     * XML test file containing an xml root and nested children.
     */
    private String xml_with_children = null;
    /**
     * XML test file containing an xml root that has attributes and no children.
     */
    private String xml_with_attributes = null;

    /**
     * XMLLoader instance to act as Unit Under Test
     */
    private XMLLoader xmlLoader = null;

    /**
     * Runs the tests in this test suite.
     *
     * @param args are ignored.
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.github.meteoorkip.util.XMLLoaderTest");
    }

    /**
     * Sets up the testing environment by loading XML files as Strings.
     *
     * @throws IOException Thrown when a file is inaccessible or cannot be found.
     */
    @Before
    public void LoadFiles() throws IOException {
        only_root = TestUtils.readFromResources("xmlfiles/only_root.xml");
        xml_with_children = TestUtils.readFromResources("xmlfiles/xml_with_children.xml");
        xml_with_attributes = TestUtils.readFromResources("xmlfiles/xml_with_attributes.xml");
        xmlLoader = new XMLLoader();
    }

    /**
     * Tests the simple functionality of reading a single xml node from a String.
     * @throws SAXException Thrown when the XML document could not be read.
     */
    @Test
    public void testReadXML() throws SAXException {
        Tree root = xmlLoader.readXML(only_root);
        assertEquals("root", root.getName());
    }

    /**
     * Tests the functionality of maintaining child structure through XML reads.
     *
     * @throws SAXException Thrown when the XML document could not be read.
     */
    @Test
    public void testReadXMLChildren() throws SAXException {
        Tree root = xmlLoader.readXML(xml_with_children);
        List<Tree> children = root.getChildren();
        assertEquals(5, children.size());
        assertEquals(8, children.get(0).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(0).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(1).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(2).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(3).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(4).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(5).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(6).getChildren().size());
        assertEquals(0, children.get(0).getChildren().get(7).getChildren().size());
        assertEquals(0, children.get(1).getChildren().size());
        assertEquals(0, children.get(2).getChildren().size());
        assertEquals(0, children.get(3).getChildren().size());
        assertEquals(0, children.get(4).getChildren().size());
    }


    @Test
    public void testReadXMLChilrenTags() throws SAXException {
        Tree root = xmlLoader.readXML(xml_with_children);
        assertEquals("car", root.getName());
        assertEquals("engine", root.getChildren().get(0).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(0).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(1).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(2).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(3).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(4).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(5).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(6).getName());
        assertEquals("cilinder", root.getChildren().get(0).getChildren().get(7).getName());
        assertEquals("wheel", root.getChildren().get(1).getName());
        assertEquals("wheel", root.getChildren().get(2).getName());
        assertEquals("wheel", root.getChildren().get(3).getName());
        assertEquals("wheel", root.getChildren().get(4).getName());
    }

    @Test
    public void testReadXMLAttributes() throws SAXException {
        Tree root = xmlLoader.readXML(xml_with_attributes);
        assertEquals("value1", root.getAttribute("attr1"));
        assertEquals("value2", root.getAttribute("attr2"));
        assertEquals("value3", root.getAttribute("attr3"));
    }

    /**
     * Tears down the test suite.
     */
    @After
    public void TearDown() { // parasoft-suppress JUNIT.CSUTD
        only_root = null;
        xml_with_children = null;
        xml_with_attributes = null;
        xmlLoader = null;
    }
} // parasoft-suppress PB.TYPO.AECB
