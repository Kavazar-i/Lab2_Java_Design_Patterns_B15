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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class DeviceInitializer {
    private static final Logger logger = Logger.getLogger(DeviceInitializer.class.getName());

    private DeviceFactory deviceFactory;
    private DeviceRepository repository;
    private Random random = new Random();

    private static final String[] VALID_DEVICE_TYPES = {"Light", "Thermostat"};
    private static final String[] VALID_STATES = {"On", "Off"};

    public DeviceInitializer(DeviceRepository repository) {
        this.repository = repository;
    }

    public boolean setDeviceFactory(DeviceFactory factory) {
        deviceFactory = factory;
        return true;
    }

    public void initializeDevicesFromFile(String filename) {
//        try {
        Gson gson = new Gson();
        Type deviceListType = new TypeToken<List<DeviceData>>() {
        }.getType();

        URL url = url = DeviceInitializer.class.getResource(filename);

        if (url == null) {
            logger.error("Url is null! Data will be generated.");
            generateRandomDevices();
            return;
        }

        String path = null;
        try {
            path = Paths.get(url.toURI()).toString();
        } catch (URISyntaxException e) {
            logger.error(String.format("URISyntaxException: %s. Data will be generated.", e));
            generateRandomDevices();
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            logger.error("File not found. Data will be generated.");
            generateRandomDevices();
        }
        List<DeviceData> devices = gson.fromJson(fileReader, deviceListType);

        if (devices == null || devices.isEmpty()) {
            generateRandomDevices();
        } else {
            for (DeviceData data : devices) {
                if (!isValidDeviceData(data)) {
                    logger.warn(String.format("Invalid device data: %s", data));
                    continue;
                }

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
    }

    private boolean isValidDeviceData(DeviceData data) {
        return isValidType(data.type) && isValidState(data.state);
    }

    private boolean isValidType(String type) {
        for (String validType : VALID_DEVICE_TYPES) {
            if (validType.equals(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidState(String state) {
        for (String validState : VALID_STATES) {
            if (validState.equals(state)) {
                return true;
            }
        }
        return false;
    }

    private void generateRandomDevices() {
        for (int i = 0; i < 5; i++) {
            String type = VALID_DEVICE_TYPES[random.nextInt(VALID_DEVICE_TYPES.length)];
            String stateStr = VALID_STATES[random.nextInt(VALID_STATES.length)];

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

    private static class DeviceData {
        String type;
        String state;
    }
}
