package com.kalosha.automatization.model.light;

import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.light.impl.LightImpl;
import com.kalosha.automatization.model.state.State;

public class Light extends Device {
    LightImpl light;

    public Light(LightImpl light) {
        super(light);
        this.light = light;
    }

    @Override
    public boolean turnOn() {
        return light.turnOn();
    }

    @Override
    public boolean turnOff() {
        return light.turnOff();
    }

    @Override
    public boolean setState(State state) {
        return light.changeState(state);
    }

    public boolean setBrightness(Integer brightness) {
        return light.setParameter(brightness);
    }

    @Override
    public boolean setParameter(Integer parameter) {
        return light.setParameter(parameter);
    }
}
