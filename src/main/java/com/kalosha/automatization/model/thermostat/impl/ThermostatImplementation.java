package com.kalosha.automatization.model.thermostat.impl;

import com.kalosha.automatization.model.device.impl.DeviceImplementation;
import com.kalosha.automatization.model.state.State;
import org.apache.log4j.Logger;


public class ThermostatImplementation implements DeviceImplementation {
    private static final Logger logger = Logger.getLogger(ThermostatImplementation.class.getName());

    private State state;
    private int temperature = 0;

    @Override
    public void turnOn() {
        logger.info("Thermostat is turned on.");
    }

    @Override
    public void turnOff() {
        logger.info("Thermostat is turned off.");
    }

    @Override
    public void changeState(State state) {
        this.state = state;
        logger.info(String.format("Thermostat state changed to %s", state.getClass().getSimpleName()));
    }

    @Override
    public void setParameter(Integer parameter) {
        this.temperature = parameter;
        logger.info(String.format("Thermostat temperature set to %d", temperature));
    }
}
