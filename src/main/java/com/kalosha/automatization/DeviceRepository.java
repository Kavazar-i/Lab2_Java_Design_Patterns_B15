package com.kalosha.automatization;

import com.kalosha.automatization.model.device.Device;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DeviceRepository {
    private final List<Device> devices = new ArrayList<>();

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Optional<Device> findDeviceByType(String deviceTypeName) {
        return devices.stream()
                .filter(device -> device.getClass().getSimpleName().equals(deviceTypeName))
                .findFirst();
    }

    public void sortDevices() {
        devices.sort((d1, d2) -> d1.getClass().getSimpleName().compareTo(d2.getClass().getSimpleName()));
    }
}
