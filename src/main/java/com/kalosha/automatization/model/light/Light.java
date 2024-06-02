package com.kalosha.automatization.model.light;

import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.light.impl.LightImplementation;
import com.kalosha.automatization.model.state.State;

public class Light extends Device {
    LightImplementation implementation;

    public Light(LightImplementation implementation) {
        super(implementation);
        this.implementation = implementation;
    }

    @Override
    public void turnOn() {
        implementation.turnOn();
    }

    @Override
    public void turnOff() {
        implementation.turnOff();
    }

    @Override
    public void setState(State state) {
        implementation.changeState(state);
    }

//    @Override
    public void setBrightness(Integer brightness) {
        implementation.setParameter(brightness);
    }

    @Override
    public void setParameter(Integer parameter) {
        implementation.setParameter(parameter);
    }
}
