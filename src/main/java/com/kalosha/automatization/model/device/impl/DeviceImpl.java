package com.kalosha.automatization.model.device.impl;

import com.kalosha.automatization.model.state.State;

public interface DeviceImpl {
    boolean turnOn();
    boolean turnOff();
    boolean changeState(State state);
    boolean setParameter(Integer parameter);
}
