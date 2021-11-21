package org.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.scanners.Scanners;
import org.reflections.serializers.JsonSerializer;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.reflections.vfs.AndroidManifest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.reflections.ReflectionsTest.getUserDir;

public class ReflectionAndroidTest {

    /**
     * When - Need to collect run time data from Android applications
     * Then - Check and fetch from the AndroidManifest.xml generated runtime file
     */
    @Test
    public void testCollectAndroid() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("org.reflections")
                        .filterInputsBy(new FilterBuilder()
                                .includePattern("org\\.reflections\\.TestModel\\$.*")
                                .includePattern("Android*\\.xml"))
                        .addScanners(Scanners.values()));

        String targetDir = getUserDir() + "/target/test-classes";

        // xml
        reflections.save(targetDir + "/META-INF/reflections/saved-testModel-AndroidManifest.xml");
        assertEquals(
                Reflections.collect("/META-INF/reflections/testModel-AndroidManifest.xml", a -> true).getStore(),
                Reflections.collect("/META-INF/reflections/saved-testModel-AndroidManifest.xml", a -> true).getStore());
    }

    /**
     * When - Need to collect Android runtime meta data
     * Then - Search for and fetch AndroidManifest.xml files
     */
    @Test
    public void testCollectFiles() {
        AndroidManifest androidManifest = new AndroidManifest();
        File testFile = new File("../");
        androidManifest.collectFiles(testFile);
        assertNotNull(AndroidManifest.fileList);
    }
}
