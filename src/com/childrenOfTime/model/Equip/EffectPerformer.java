package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Warriors.Warrior;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by SaeedHD on 07/09/2016.
 */
public class EffectPerformer {

    private static boolean doesPassiveAllowsToContinue(Effects effect, Warrior performer) {
        if (effect.getEffectType().isPassive()) {
            if (performer.containsPassiveEffect(effect)) return true;
            performer.addPassiveEffect(effect);
            return false;
        }
        return true;
    }

    //TODO anotherCheck required
    public static void performEffects(Collection<Effects> effects, Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        Iterator<Effects> itr = effects.iterator();
        Warrior[] finalTargets;

        while (itr.hasNext()) {
            Effects nextEffect = itr.next();
            if (!doesPassiveAllowsToContinue(nextEffect, performer)) return;
            finalTargets = chooseTargts(nextEffect, performer, selectedTargets, allEnemies, allTeamMates);
            nextEffect.perform(finalTargets);

        }
    }

    private static Warrior[] chooseTargts(Effects eff, Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        Warrior[] newTargets = null;
        switch (eff.getTargetType()) {
            case HimSelf:
                newTargets = new Warrior[1];
                newTargets[0] = performer;
                break;
            case AllEnemies:
                newTargets = allEnemies;
                break;
            case AllTeammates:
                newTargets = allTeamMates;
                break;
            case SingleTarget:
            case SeveralEnemies:
            case SeveralTeamMates:
                newTargets = selectedTargets;
                break;

        }
        return newTargets;
    }
}


/**
 * public static void performEffects(List<Effects> effects, @NotNull Warrior performer, @Nullable Warrior[] target_s, @Nullable Warrior... implicitTargets){
 * Iterator<Effects> itr = effects.iterator();
 * Warrior[] finalTargets = target_s;
 * while (itr.hasNext()) {
 * //TODO anotherCheck required
 * Effects nextEffect = itr.next();
 * if (nextEffect.getEffectType().isTargetUnChoosable() && implicitTargets != null) {
 * finalTargets = implicitTargets;
 * }
 * if (finalTargets != null) {
 * <p>
 * nextEffect.perform(finalTargets);
 * } else throw new RuntimeException("no Targets Selected");
 * if (nextEffect.getEffectType().isPassive()) {
 * itr.remove();
 * }
 * }
 * <p>
 * }
 */


