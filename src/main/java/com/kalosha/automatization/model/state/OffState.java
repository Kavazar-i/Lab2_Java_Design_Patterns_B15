package com.kalosha.automatization.model.state;

import com.kalosha.automatization.model.device.impl.DeviceImpl;

public class OffState extends State {

    public OffState(DeviceImpl implementation) {
        super(implementation);
    }

    @Override
    boolean setOn() {
        return device.changeState(new OnState(device));
    }

    @Override
    boolean setOff() {
        return false;
    }
}
