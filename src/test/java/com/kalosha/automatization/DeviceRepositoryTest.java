package com.kalosha.automatization;

import com.kalosha.automatization.creator.DeviceFactory;
import com.kalosha.automatization.creator.SmartHomeFactory;
import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.light.Light;
import com.kalosha.automatization.model.thermostat.Thermostat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeviceRepositoryTest {
    private DeviceRepository repository;
    private Device light;
    private Device thermostat;

    @Before
    public void setUp() {
        repository = new DeviceRepository();
        DeviceFactory factory = new SmartHomeFactory();
        light = factory.createLight();
        thermostat = factory.createThermostat();
    }

    @Test
    public void testAddAndGetDevices() {
        repository.addDevice(light);
        repository.addDevice(thermostat);

        assertEquals(2, repository.getDevices().size());
        assertTrue(repository.getDevices().contains(light));
        assertTrue(repository.getDevices().contains(thermostat));
    }

    @Test
    public void testFindDeviceByType() {
        repository.addDevice(light);
        repository.addDevice(thermostat);

        Device foundLight = repository.findDeviceByType("Light");
        Device foundThermostat = repository.findDeviceByType("Thermostat");

        assertNotNull(foundLight);
        assertNotNull(foundThermostat);
    }

    @Test
    public void testSortDevices() {
        repository.addDevice(thermostat);
        repository.addDevice(light);

        repository.sortDevices();

        List<Device> devices = repository.getDevices();
        assertEquals(Light.class, devices.get(0).getClass());
        assertEquals(Thermostat.class, devices.get(1).getClass());
    }
}

