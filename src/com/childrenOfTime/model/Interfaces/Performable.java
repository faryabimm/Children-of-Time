package com.childrenOfTime.model.Interfaces;

import com.childrenOfTime.model.Warrior;
import com.childrenOfTime.model.Warriors.Hero;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public interface Performable {
    void perform(Hero performer, Warrior... target_s);
}
