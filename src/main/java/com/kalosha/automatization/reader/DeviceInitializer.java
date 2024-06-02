package com.kalosha.automatization.reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kalosha.automatization.DeviceRepository;
import com.kalosha.automatization.creator.DeviceFactory;
import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.state.OffState;
import com.kalosha.automatization.model.state.OnState;
import com.kalosha.automatization.model.state.State;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DeviceInitializer {
    private static final Logger logger = Logger.getLogger(DeviceInitializer.class.getName());

    private DeviceFactory deviceFactory;
    private DeviceRepository repository;
    private Random random = new Random();


    public DeviceInitializer(DeviceFactory deviceFactory, DeviceRepository repository) {
        this.deviceFactory = deviceFactory;
        this.repository = repository;
    }

    public void initializeDevicesFromFile(String filename) {
        try {
            Gson gson = new Gson();
            Type deviceListType = new TypeToken<List<DeviceData>>() {
            }.getType();
            String path = Paths.get(DeviceInitializer.class.getResource(filename).toURI()).toString();
            FileReader fileReader = new FileReader(path);
            List<DeviceData> devices = gson.fromJson(fileReader, deviceListType);

            if (devices == null || devices.isEmpty()) {
                generateRandomDevices();
            } else {
                for (DeviceData data : devices) {
                    Device device;
                    if (data.type.equals("Light")) {
                        device = deviceFactory.createLight();
                    } else if (data.type.equals("Thermostat")) {
                        device = deviceFactory.createThermostat();
                    } else {
                        continue;
                    }
                    State state = data.state.equals("On") ? new OnState(device.getImplementation()) : new OffState(device.getImplementation());
                    device.setState(state);
                    repository.addDevice(device);
                    logger.info(String.format("Initialized %s with state %s", data.type, data.state));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            generateRandomDevices();
        }
    }

    private void generateRandomDevices() {
        String[] deviceTypes = {"Light", "Thermostat"};
        String[] states = {"On", "Off"};

        for (int i = 0; i < 5; i++) {
            String type = deviceTypes[random.nextInt(deviceTypes.length)];
            String stateStr = states[random.nextInt(states.length)];

            Device device;
            if (type.equals("Light")) {
                device = deviceFactory.createLight();
            } else {
                device = deviceFactory.createThermostat();
            }

            State state = stateStr.equals("On") ? new OnState(device.getImplementation()) : new OffState(device.getImplementation());
            device.setState(state);
            repository.addDevice(device);
            logger.info(String.format("Generated %s with state %s", type, stateStr));
        }
    }
}
