package com.kalosha.automatization.creator;

import com.kalosha.automatization.model.light.Light;
import com.kalosha.automatization.model.light.impl.LightImplementation;
import com.kalosha.automatization.model.thermostat.Thermostat;
import com.kalosha.automatization.model.thermostat.impl.ThermostatImplementation;

public class SmartHomeFactory implements DeviceFactory {
    @Override
    public Light createLight() {
        return new Light(new LightImplementation());
    }

    @Override
    public Thermostat createThermostat() {
        return new Thermostat(new ThermostatImplementation());
    }
}