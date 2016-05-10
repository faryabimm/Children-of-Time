package com.childrenOfTime.model;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public abstract class Item implements Durable {
    protected int initialPrice;
    protected int priceIncreament;
    private int size;
    private int duration;
    private String name;

    public void use(Warrior warrior) {
    }

    @Override
    public void wearOff() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
