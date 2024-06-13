package com.kalosha.automatization.model.state;

import com.kalosha.automatization.model.device.impl.DeviceImpl;

public class OnState extends State {

    public OnState(DeviceImpl implementation) {
        super(implementation);
    }

    @Override
    boolean setOn() {
        return false;
    }

    @Override
    boolean setOff() {
        return device.changeState(new OffState(device));
    }
}