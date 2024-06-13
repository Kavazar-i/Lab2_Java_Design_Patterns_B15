package com.kalosha.automatization;

import com.kalosha.automatization.creator.DeviceFactory;
import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.reader.DeviceInitializer;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

// Тестирующий класс
public class SmartHomeApp {
    private static final Logger logger = Logger.getLogger(SmartHomeApp.class.getName());

    public static void main(String[] args) {
        DeviceRepository repository = new DeviceRepository();
        DeviceInitializer initializer = new DeviceInitializer(repository);

        initializer.initializeDevicesFromFile("/devices.json");

        repository.sortDevices();

        for (Device device : repository.getDevices()) {
            logger.info(device.toString());
            device.turnOn();  // включить все устройства для демонстрации
            logger.info(device.toString());
        }
    }
}
