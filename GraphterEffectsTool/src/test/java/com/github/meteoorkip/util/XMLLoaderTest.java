package com.github.meteoorkip.util;

import org.junit.After;
import org.junit.Before;
import testutils.TestUtils;

import java.io.IOException;


public class XMLLoaderTest {


    private String sample_xml_1 = null;
    private XMLLoader xmlLoader = null;

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.github.meteoorkip.util.XMLLoaderTest");
    }

    @Before
    public void LoadFiles() throws IOException {
        sample_xml_1 = TestUtils.readFromResources("xmlfiles/sample.xml");
        xmlLoader = new XMLLoader();
    }

    @After
    public void TearDown() {
        sample_xml_1 = null;
    }

}
