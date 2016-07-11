package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public interface Performable extends Serializable {
    void perform(Warrior... Target_s);
}
