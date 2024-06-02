package com.kalosha.automatization.model.device.impl;

import com.kalosha.automatization.model.state.State;

public interface DeviceImplementation{
    void turnOn();
    void turnOff();
    void changeState(State state);
    void setParameter(Integer parameter);
}
