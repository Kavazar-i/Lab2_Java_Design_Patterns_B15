package com.kalosha.automatization.model.state;

import com.kalosha.automatization.model.device.impl.DeviceImplementation;

public abstract class State {
    DeviceImplementation implementation;

    public State(DeviceImplementation implementation) {
        this.implementation = implementation;
    }

    abstract void setOn();
    abstract void setOff();
}
