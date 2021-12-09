package AndroidManifest;

import org.reflections.vfs.ManifestInformation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AndroidCollector {

    List<File> fileList = new ArrayList<File>();
    String androidFile = "AndroidManifest.xml";

    /**
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

    public void setAndroidFile(String androidFile) {
        this.androidFile = androidFile;
    }

    public String parseFile(File file) throws ParserConfigurationException, SAXException, IOException {
        ManifestInformation androidFileInfo = new ManifestInformation(androidFile, file);
        return androidFileInfo.toString();
    }
}
