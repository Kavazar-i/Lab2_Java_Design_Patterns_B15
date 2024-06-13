package com.kalosha.automatization.creator;

import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.thermostat.Thermostat;
import com.kalosha.automatization.model.thermostat.impl.ThermostatImpl;

public class TermostatFactory implements DeviceFactory {
    @Override
    public Device createDevice() {
        return new Thermostat(new ThermostatImpl());
    }
}
