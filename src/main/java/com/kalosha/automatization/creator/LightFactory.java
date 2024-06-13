package com.kalosha.automatization.creator;

import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.light.Light;
import com.kalosha.automatization.model.light.impl.LightImpl;

public class LightFactory implements DeviceFactory{
    @Override
    public Device createDevice() {
        return new Light(new LightImpl());
    }
}
