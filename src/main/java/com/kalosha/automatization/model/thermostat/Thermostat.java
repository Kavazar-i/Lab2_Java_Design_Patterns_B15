package com.kalosha.automatization.model.thermostat;

import com.kalosha.automatization.model.device.Device;
import com.kalosha.automatization.model.state.State;
import com.kalosha.automatization.model.thermostat.impl.ThermostatImpl;

public class Thermostat extends Device {
    ThermostatImpl thermostat;

    public Thermostat(ThermostatImpl thermostat) {
        super(thermostat);
        this.thermostat = thermostat;
    }

    @Override
    public boolean turnOn() {
        return thermostat.turnOn();
    }

    @Override
    public boolean turnOff() {
        return thermostat.turnOff();
    }

    @Override
    public boolean setState(State state) {
        return thermostat.changeState(state);
    }

    public boolean setTemperature(int temperature) {
        return thermostat.setParameter(temperature);
    }


    @Override
    public boolean setParameter(Integer parameter) {
        return thermostat.setParameter(parameter);
    }
}
