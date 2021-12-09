package org.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.vfs.ManifestInformation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManifestScenarioTest {

    String testFilePath = "src/test/resources/AndroidManifest.xml";

    /**
     * <p> Check the scenario by getting manifest file and parsing it
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void testManifestScenario() throws ParserConfigurationException, SAXException, IOException {
        // create Android collector
        AndroidManifest.AndroidCollector androidCollector = new AndroidManifest.AndroidCollector();
        File file = new File(testFilePath);
        androidCollector.collectFiles(file);

        // create the Manifest info
        ManifestInformation manifestInformation = new ManifestInformation(testFilePath, file);

        // check and get one attribute from file
        assertTrue(manifestInformation.checkTag("uses-permission"));
        List<String> retVals = (ArrayList<String>) manifestInformation.getAttribute("action");
        assertNotNull(manifestInformation.manifestItems);
        assertTrue(retVals.stream().anyMatch(element -> element.contains("action")));
    }
}
