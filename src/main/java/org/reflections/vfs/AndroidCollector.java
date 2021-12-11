package AndroidManifest;

import org.reflections.vfs.ManifestInformation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AndroidCollector {

    public List<File> fileList = new ArrayList<File>();
    String androidFile = "AndroidManifest.xml";

    /**
     * CS427 https://github.com/ronmamo/reflections/issues/339
     * <p> Go through the project and look for Android Runtime files
     * @param file
     */
    public void collectFiles(File file)
    {
        File[] dirFiles = file.listFiles();
        if(dirFiles != null) {
            for (File currFile : dirFiles) {
                if (currFile.isDirectory()) {
                    collectFiles(currFile);
                } else if (currFile.getName().contains(androidFile)) {
                    fileList.add(currFile);
                }
            }
        }
    }

    /**
     * CS427 https://github.com/ronmamo/reflections/issues/339
     * <p> Set the location of android file
     * @param androidFile String name of file
     */
    public void setAndroidFile(String androidFile) {
        this.androidFile = androidFile;
    }

    /**
     * CS427 https://github.com/ronmamo/reflections/issues/339
     * @param file manifest file
     * @return return the info of the file in string format
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public String parseFile(File file) throws ParserConfigurationException, SAXException, IOException {
        ManifestInformation androidFileInfo = new ManifestInformation(androidFile, file);
        return androidFileInfo.toString();
    }
}
