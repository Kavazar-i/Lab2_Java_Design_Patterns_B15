package com.kalosha.automatization.model.state;

import com.kalosha.automatization.model.device.impl.DeviceImpl;

public abstract class State {
    DeviceImpl device;

    public State(DeviceImpl device) {
        this.device = device;
    }

    abstract boolean setOn();
    abstract boolean setOff();
}
