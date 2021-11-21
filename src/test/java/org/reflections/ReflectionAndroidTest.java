package org.reflections;

import org.junit.jupiter.api.Test;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class ReflectionAndroidTest {

    /**
     * When - Need to collect run time data from Android applications
     * Then - Check and fetch from the Android.xml generated runtime file
     */
    @Test
    public void testCollectAndroid() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .forPackage("org.reflections")
                .filterInputsBy(new FilterBuilder()
                        .includePattern("org\\.reflections\\.TestModel\\$.*")
                        .includePattern("Android\\.xml"))
                .addScanners(Scanners.values());
        Reflections reflections = new Reflections();
    }
}
