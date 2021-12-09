package org.reflections.vfs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManifestInformation {
    private String fileName;
    public ArrayList<String> manifestItems = new ArrayList<String>();
    public ManifestInformation(String fileName, File file) throws IOException, SAXException, ParserConfigurationException {
        this.fileName = fileName;
        scanFile(file);
    }

    /**
     * <p> Goes through manifest file and grabs elements
     * @param file manifest file
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private void scanFile(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList=doc.getElementsByTagName("* 3e");
        for (int i=0; i<nodeList.getLength(); i++)
        {
            Element element = (Element)nodeList.item(i);
            addItem(element.getNodeName());
        }
    }

    /**
     * <p> Validate the existence of an xml tag in manifest file
     * @param flag
     * @return
     */
    public boolean checkTag(String flag){
        return manifestItems.stream().anyMatch(element -> element.contains(flag));
    }

    /**
     * <p> Add an item to the manifest information
     * @param item
     */
    public void addItem(String item) {
        manifestItems.add(item);
    }

    /**
     * <p> Gets the list of tags that match the item name
     * @param itemName
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public ArrayList<String> getAttribute(String itemName) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        ArrayList<String> foundElements = new ArrayList<>();
        NodeList nodeList=doc.getElementsByTagName(itemName);
        for (int i=0; i<nodeList.getLength(); i++)
        {
            Element element = (Element)nodeList.item(i);
            foundElements.add(element.toString());
        }
        return foundElements;
    }

    /**
     *
     * @return String format of manifest information
     */
    @Override
    public String toString() {
        return "ManifestInformation{" +
                "fileName='" + fileName + '\'' +
                ", manifestItems=" + manifestItems.toString() +
                '}';
    }
}
