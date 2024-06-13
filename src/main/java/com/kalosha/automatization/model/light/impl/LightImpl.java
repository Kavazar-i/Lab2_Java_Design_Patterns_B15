package com.kalosha.automatization.model.light.impl;

import com.kalosha.automatization.model.device.impl.DeviceImpl;
import com.kalosha.automatization.model.state.State;
import org.apache.log4j.Logger;

public class LightImpl implements DeviceImpl {
    private static final Logger logger = Logger.getLogger(LightImpl.class.getName());
    private State state;
    private Integer brightness = 0;

    @Override
    public boolean turnOn() {
        logger.info("Light is turned on.");
        return true;
    }

    @Override
    public boolean turnOff() {
        logger.info("Light is turned off.");
        return true;
    }

    @Override
    public boolean changeState(State state) {
        this.state = state;
        logger.info(String.format("Light state changed to %s", state.getClass().getSimpleName()));
        return true;
    }

    @Override
    public boolean setParameter(Integer parameter) {
        brightness = parameter;
        logger.info(String.format("Light parameter changed to %d", parameter));
        return true;
    }
}
