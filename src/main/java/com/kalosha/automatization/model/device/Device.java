package com.kalosha.automatization.model.device;

import com.kalosha.automatization.model.device.impl.DeviceImplementation;
import com.kalosha.automatization.model.state.OffState;
import com.kalosha.automatization.model.state.State;

public abstract class Device {
    private DeviceImplementation implementation;
    private State state;

    protected Device(DeviceImplementation implementation) {
        this.implementation = implementation;
        this.state = new OffState(this.implementation);
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public abstract void setState(State state);

    public abstract void setParameter(Integer parameter);

    public DeviceImplementation getImplementation() {
        return implementation;
    }
}
