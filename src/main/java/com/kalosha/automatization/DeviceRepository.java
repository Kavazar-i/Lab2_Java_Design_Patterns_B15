package com.kalosha.automatization;

import com.kalosha.automatization.model.device.Device;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeviceRepository {
    private List<Device> devices = new ArrayList<>();

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device findDeviceByType(Class<? extends Device> deviceType) {
        return devices.stream()
                .filter(deviceType::isInstance)
                .findFirst()
                .orElse(null);
    }

    public void sortDevices() {
        devices.sort((d1, d2) -> d1.getClass().getSimpleName().compareTo(d2.getClass().getSimpleName()));
    }
}
