package com.kalosha.automatization.model.state;

import com.kalosha.automatization.model.device.impl.DeviceImplementation;

public class OnState extends State {

    public OnState(DeviceImplementation implementation) {
        super(implementation);
    }

    @Override
    void setOn() {

    }

    @Override
    void setOff() {
        implementation.changeState(new OffState(implementation));
    }
}