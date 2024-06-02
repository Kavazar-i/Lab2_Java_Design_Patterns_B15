package com.kalosha.automatization;

import com.kalosha.automatization.creator.DeviceFactory;
import com.kalosha.automatization.creator.SmartHomeFactory;
import com.kalosha.automatization.reader.DeviceInitializer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeviceInitializerTest {
    private DeviceFactory deviceFactory;
    private DeviceRepository repository;
    private DeviceInitializer initializer;

    @Before
    public void setUp() {
        deviceFactory = new SmartHomeFactory();
        repository = new DeviceRepository();
        initializer = new DeviceInitializer(deviceFactory, repository);
    }

    @Test
    public void testInitializeDevicesFromFile() {
        initializer.initializeDevicesFromFile("/devices.json");

        assertFalse(repository.getDevices().isEmpty());
        assertEquals(2, repository.getDevices().size());
    }

    @Test
    public void testGenerateRandomDevices() {
        initializer.initializeDevicesFromFile("/non_existent_file.json");

        assertFalse(repository.getDevices().isEmpty());
        assertEquals(5, repository.getDevices().size());
    }
}
