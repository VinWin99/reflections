package org.reflections.vfs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AndroidManifest {
    public static List<File> fileList = new ArrayList<File>();
    public static String androidFile = "AndroidManifest.xml";

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
}
