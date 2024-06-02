package com.kalosha.automatization.model.state;

import com.kalosha.automatization.model.device.impl.DeviceImplementation;

public class OffState extends State {

    public OffState(DeviceImplementation implementation) {
        super(implementation);
    }

    @Override
    void setOn() {
        implementation.changeState(new OnState(implementation));
    }

    @Override
    void setOff() {

    }
}
