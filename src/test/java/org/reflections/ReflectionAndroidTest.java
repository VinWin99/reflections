package org.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.scanners.Scanners;
import org.reflections.serializers.JsonSerializer;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.reflections.ReflectionsTest.getUserDir;

public class ReflectionAndroidTest {

    /**
     * When - Need to collect run time data from Android applications
     * Then - Check and fetch from the Android.xml generated runtime file
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
        reflections.save(targetDir + "/META-INF/reflections/saved-testModel-Android.xml");
        assertEquals(
                Reflections.collect("/META-INF/reflections/testModel-Android.xml", a -> true).getStore(),
                Reflections.collect("/META-INF/reflections/saved-testModel-Android.xml", a -> true).getStore());

        // json
        reflections.save(targetDir + "/META-INF/reflections/saved-testModel-Android.json", new JsonSerializer());
        assertEquals(
                Reflections.collect("/META-INF/reflections/testModel-Android.json", a -> true).getStore(),
                Reflections.collect("/META-INF/reflections/saved-testModel-Android.json", a -> true).getStore());
    }
}
