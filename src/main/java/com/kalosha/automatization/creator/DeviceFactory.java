package com.kalosha.automatization.creator;

import com.kalosha.automatization.model.light.Light;
import com.kalosha.automatization.model.thermostat.Thermostat;

public interface DeviceFactory {
    Light createLight();
    Thermostat createThermostat();
}