package org.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.vfs.ManifestInformation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class ManifestInformationTests {

    String testFilePath = "src/test/resources/AndroidManifest.xml";

    /**
     * <p> check that flag exists
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void checkFlagPositive() throws ParserConfigurationException, SAXException, IOException {
        File test = new File(testFilePath);
        ManifestInformation manifestInformation = new ManifestInformation(test.getName(), test);
        assertTrue(manifestInformation.checkTag("uses-permission"));
    }

    /**
     * <p> check that flag does not exist
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void checkFlagNegative() throws ParserConfigurationException, SAXException, IOException {
        File test = new File(testFilePath);
        ManifestInformation manifestInformation = new ManifestInformation(test.getName(), test);
        assertFalse(manifestInformation.checkTag(UUID.randomUUID().toString()));
    }

    /**
     * <p> check if can add an item
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void addItemTest() throws ParserConfigurationException, SAXException, IOException {
        File test = new File(testFilePath);
        ManifestInformation manifestInformation = new ManifestInformation(test.getName(), test);
        manifestInformation.manifestItems.clear();
        manifestInformation.addItem("item");
        assertNotNull(manifestInformation.manifestItems.get(0));
        assertEquals(manifestInformation.manifestItems.get(0), "item");
    }

    /**
     * <p> check if can get items
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void getAttributeTest() throws ParserConfigurationException, SAXException, IOException {
        File test = new File(testFilePath);
        ManifestInformation manifestInformation = new ManifestInformation(testFilePath, test);
        List<String> retVals = manifestInformation.getAttribute("action");
        assertNotNull(manifestInformation.manifestItems);
        assertTrue(retVals.stream().anyMatch(element -> element.contains("action")));
    }


}
