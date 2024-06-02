package com.kalosha.automatization.model.thermostat;

import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.device.impl.DeviceImplementation;
import com.kalosha.automatization.model.light.impl.LightImplementation;
import com.kalosha.automatization.model.state.State;
import com.kalosha.automatization.model.thermostat.impl.ThermostatImplementation;

public class Thermostat extends Device {
    ThermostatImplementation implementation;

    public Thermostat(ThermostatImplementation implementation) {
        super(implementation);
        this.implementation = implementation;
    }

//    @Override
    public void setTemperature(int temperature) {
        implementation.setParameter(temperature);
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void setState(State state) {

    }

    @Override
    public void setParameter(Integer parameter) {
        implementation.setParameter(parameter);
    }
}
