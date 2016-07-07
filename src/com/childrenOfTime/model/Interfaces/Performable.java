package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warrior;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public interface Performable {
    void perform(Warrior performer, Warrior... target_s);
}
