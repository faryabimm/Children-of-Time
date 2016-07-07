package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Interfaces.Performable;
import com.childrenOfTime.model.Warrior;
import com.childrenOfTime.model.Warriors.Hero;

/**
 * Created by SaeedHD on 07/07/2016.
 */
public abstract class Effects implements Performable {
    public static final Double YEK_DOUBLE = 1d;

    @Override
    public abstract void perform(Hero performer, Warrior... target_s);


}
