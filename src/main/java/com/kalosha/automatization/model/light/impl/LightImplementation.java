package com.kalosha.automatization.model.light.impl;

import com.kalosha.automatization.model.device.impl.DeviceImplementation;
import com.kalosha.automatization.model.state.State;
import org.apache.log4j.Logger;

public class LightImplementation implements DeviceImplementation {
    private static final Logger logger = Logger.getLogger(LightImplementation.class.getName());
    private State state;
    private Integer brightness = 0;

    @Override
    public void turnOn() {
        logger.info("Light is turned on.");
    }

    @Override
    public void turnOff() {
        logger.info("Light is turned off.");
    }

    @Override
    public void changeState(State state) {
        this.state = state;
        logger.info(String.format("Light state changed to %s", state.getClass().getSimpleName()));
    }

    @Override
    public void setParameter(Integer parameter) {
        brightness = parameter;
        logger.info(String.format("Light parameter changed to %d", parameter));
    }
}
