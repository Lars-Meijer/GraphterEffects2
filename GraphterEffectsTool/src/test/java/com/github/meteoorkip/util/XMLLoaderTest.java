package com.github.meteoorkip.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import testutils.TestUtils;

import java.io.IOException;

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
        xmlLoader = new XMLLoader();
    }

    /**
     * Tests the simple functionality of reading a single xml node from a String.
     */
    @Test
    public void testReadXML() throws SAXException {
        Tree root = xmlLoader.readXML(only_root);
        assertEquals("root", root.getName());
    }

    /**
     * Tears down the test suite.
     */
    @After
    public void TearDown() { // parasoft-suppress JUNIT.CSUTD
        only_root = null;
        xmlLoader = null;
    }
} // parasoft-suppress PB.TYPO.AECB
