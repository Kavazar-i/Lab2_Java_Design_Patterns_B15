package com.kalosha.automatization.model.device;

import com.kalosha.automatization.model.device.impl.DeviceImpl;
import com.kalosha.automatization.model.state.OffState;
import com.kalosha.automatization.model.state.State;

public abstract class Device {
    private DeviceImpl device;
    private State state;

    protected Device(DeviceImpl device) {
        this.device = device;
        this.state = new OffState(this.device);
    }

    public abstract boolean turnOn();

    public abstract boolean turnOff();

    public abstract boolean setState(State state);

    public abstract boolean setParameter(Integer parameter);

    public DeviceImpl getDevice() {
        return device;
    }
}
