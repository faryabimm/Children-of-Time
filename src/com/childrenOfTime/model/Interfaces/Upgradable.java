package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public interface Upgradable extends Serializable {
    Integer upgrade(Warrior upgrader, Integer i);
}
