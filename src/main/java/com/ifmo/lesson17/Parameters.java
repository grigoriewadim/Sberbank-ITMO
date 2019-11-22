package com.ifmo.lesson17;

public abstract class Car {

    public abstract String getMaxPower();
    public abstract String getMaxSpeed();

    @Override
    public String toString(){
        return "Max speed= "+this.getMaxSpeed()+", MaxPower="+this.getMaxPower();
    }
}
