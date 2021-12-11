package org.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.reflections.ReflectionsTest.getUserDir;

public class ReflectionAndroidTest {

    /**
     * CS427 https://github.com/ronmamo/reflections/issues/339
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
     * CS427 https://github.com/ronmamo/reflections/issues/339
     * When - Need to collect Android runtime meta data
     * Then - Search for and fetch AndroidManifest.xml files
     */
    @Test
    public void testCollectFiles() {
        AndroidManifest.AndroidCollector androidManifest = new AndroidManifest.AndroidCollector();
        File testFile = new File("../");
        androidManifest.collectFiles(testFile);
        assertNotNull(androidManifest.fileList);
    }
}
