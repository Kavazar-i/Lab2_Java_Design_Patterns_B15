package com.kalosha.automatization.reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kalosha.automatization.DeviceRepository;
import com.kalosha.automatization.creator.DeviceFactory;
import com.kalosha.automatization.creator.LightFactory;
import com.kalosha.automatization.creator.TermostatFactory;
import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.state.OffState;
import com.kalosha.automatization.model.state.OnState;
import com.kalosha.automatization.model.state.State;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class DeviceInitializer {
    private static final Logger logger = Logger.getLogger(DeviceInitializer.class.getName());

    private DeviceFactory deviceFactory;
    private DeviceRepository repository;
    private Random random = new Random();


    public DeviceInitializer(DeviceRepository repository) {
        this.repository = repository;
    }

    public boolean setDeviceFactory(DeviceFactory factory) {
        deviceFactory = factory;
        return true;
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
                        setDeviceFactory(new LightFactory());
                        device = deviceFactory.createDevice();
                    } else if (data.type.equals("Thermostat")) {
                        setDeviceFactory(new TermostatFactory());
                        device = deviceFactory.createDevice();
                    } else {
                        continue;
                    }
                    State state = data.state.equals("On") ? new OnState(device.getDevice()) : new OffState(device.getDevice());
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
                setDeviceFactory(new LightFactory());
                device = deviceFactory.createDevice();
            } else {
                setDeviceFactory(new TermostatFactory());
                device = deviceFactory.createDevice();
            }

            State state = stateStr.equals("On") ? new OnState(device.getDevice()) : new OffState(device.getDevice());
            device.setState(state);
            repository.addDevice(device);
            logger.info(String.format("Generated %s with state %s", type, stateStr));
        }
    }
}
