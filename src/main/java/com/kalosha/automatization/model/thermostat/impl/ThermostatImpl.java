package com.kalosha.automatization.model.thermostat.impl;

import com.kalosha.automatization.model.device.impl.DeviceImpl;
import com.kalosha.automatization.model.state.State;
import org.apache.log4j.Logger;


public class ThermostatImpl implements DeviceImpl {
    private static final Logger logger = Logger.getLogger(ThermostatImpl.class.getName());

    private State state;
    private int temperature = 0;

    @Override
    public boolean turnOn() {
        logger.info("Thermostat is turned on.");
        return true;
    }

    @Override
    public boolean turnOff() {
        logger.info("Thermostat is turned off.");
        return true;
    }

    @Override
    public boolean changeState(State state) {
        this.state = state;
        logger.info(String.format("Thermostat state changed to %s", state.getClass().getSimpleName()));
        return true;
    }

    @Override
    public boolean setParameter(Integer parameter) {
        this.temperature = parameter;
        logger.info(String.format("Thermostat temperature set to %d", temperature));
        return true;
    }
}
