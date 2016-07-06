package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.InProgress;
import com.childrenOfTime.exceptions.AttackException;
import com.childrenOfTime.exceptions.ItemNotAquiredException;
import com.childrenOfTime.exceptions.NotEnoughEnergyPointsException;
import com.childrenOfTime.exceptions.NotEnoughMagicPointsException;

import java.util.*;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Hero extends Warrior implements HasImpactHealth {
    protected int currentMagic;
    protected int currentEnergyPoints;
    protected Inventory inventory;
    private TypesOfHero typesOfHero;

    //for Swirling ability
    boolean swirlingisActivated = false;
    public double damagePercent;



    InformationOfHeroes info;
    Map<String, Ability> abilities = new HashMap<>();
    public int probability = 0;
    public boolean criticalIsActivated = false;


    @Override
    public void attack(Warrior enemy, Integer realAttack, Integer EPCost) throws NotEnoughEnergyPointsException {
        if (EPCost == null) EPCost = 2;
        changeEP(-EPCost);
        if (realAttack == null) realAttack = this.attackPower;

        if (this.criticalIsActivated) {
            realAttack = getAttackPowerByCriticalActivation();
        }
        printOutput(WarriorMessages.getSuccessfulAttackMessage(this, enemy, realAttack));
        enemy.changeHealth(-realAttack);
        if (this.swirlingisActivated) {
            doSwirlingAttack(realAttack);
        }
    }

    @Override
    public void heal(Warrior warrior, Integer healingAmount) {

    }

    private void doSwirlingAttack(int realAttack) {
        List<Warrior> allEnemies = new ArrayList<>() /*Battle.getFoes()*/; //TODO rideman ro jam kon !
        for (Warrior warrior : allEnemies) {     //access to AllFoes
            if (warrior.equals(warrior)) continue;
            warrior.changeAttackPower((int) (this.damagePercent * realAttack));
        }
        printOutput(WarriorMessages.getSuccessfulSwirlingAttackMessage(this, allEnemies, (int) (this.damagePercent * realAttack)));
    }

    private int getAttackPowerByCriticalActivation() {
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        if (n <= probability) {
            return attackPower * 2;
        }
        return attackPower;
    }

    /*
        @Completed
        public void attack(Foe enemy, int attackPower) {
            printOutput(WarriorMessages.getSuccessfulAttackMessage(this, enemy, attackPower));
            enemy.changeHealth(-attackPower);
            if (this.swirlingisActivated) {
                doSwirlingAttack(attackPower);

            }
        }
    */
    @Completed
    public void showAbDes() {
        String toPrint = "";
        String state = "";
        for (Map.Entry<String, Ability> entry : abilities.entrySet()) {
            if (entry.getValue().currentLevel == 0) {
                state = "not acquired";
            } else state = entry.getValue().currentLevel + "";
            toPrint += "\t" + entry.getKey() + " : " + state + "\n";
        }
        printOutput(toPrint);
    }

    public Hero(FighterHero name, TypesOfHero className, int id) {
        this(name.name(), className.name(), id);
    }


    public Hero(SupporterHero name, TypesOfHero className, int id) {
        this(name.name(), className.name(), id);
    }


    @Completed
    public Hero(String name, String className, int id) {    //TODO It Should Be Private
        super(name, id);
        typesOfHero = TypesOfHero.valueOf(className);
        this.info = new InformationOfHeroes(typesOfHero.healthRefillRate, typesOfHero.inventorySize,
                typesOfHero.maxMagic, typesOfHero.magicRefillRate, typesOfHero.initialEP, typesOfHero.ability1, typesOfHero.ability2, typesOfHero.classDescription, "");

        switch (typesOfHero) {
            case Supporter:
                SupporterHero heroName=SupporterHero.valueOf(name);
                info.ability3=heroName.ability3;
                info.ability4=heroName.ability4;
                info.heroDescription = heroName.heroDescription;
                break;
            case Fighter:
                FighterHero heroName2=FighterHero.valueOf(name);
                info.ability3=heroName2.ability3;
                info.ability4=heroName2.ability4;
                info.heroDescription = heroName2.heroDescription;
                break;
        }
        setAbilities(info);
        setCurrentMagic(info.maxMagic);
        setCurrentEnergyPoints(info.initialEP);
        setInventory(new Inventory(info.inventorySize));

        super.currentHealth = typesOfHero.maxHealth;
        super.attackPower = typesOfHero.attackPower;
        super.maxHealth = typesOfHero.maxHealth;
    }
    @Completed
    public ArrayList<Item> getInventoryItems() {
        return inventory.getItems();
    }
    @Completed
    public void addToInventory(Item item){
        inventory.getItems().add(item);
    }
    @Completed
    public void removeFromInventory(Item item) {
        this.inventory.getItems().remove(item);
    }

    @Completed
    public void useItem(String itemName, Warrior warrior) throws ItemNotAquiredException {

    }
    @Completed
    public String toString() {

        return this.getName() + " (" + this.typesOfHero + ") - ";
    }
    @Completed
    private void setAbilities(InformationOfHeroes info){
        abilities.put(info.ability1,new Ability(info.ability1));
        abilities.put(info.ability2,new Ability(info.ability2));
        abilities.put(info.ability3,new Ability(info.ability3));
        abilities.put(info.ability4,new Ability(info.ability4));
    }
    @InProgress
    public void useAbility(Ability ability, Warrior warrior) throws AttackException {
        ability.cast(this, warrior);
    }
    public int getInventorySize() {
        return typesOfHero.inventorySize;
    }
    public void setInventorySize(int inventorySize) {
        this.typesOfHero.inventorySize = inventorySize;
    }
    public int getMaxMagic() {
        return typesOfHero.maxMagic;
    }
    public void setMaxMagic(int maxMagic) {
        this.typesOfHero.maxMagic = maxMagic;
    }
    public int getCurrentMagic() {
        return currentMagic;
    }
    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }
    public int getMagicRefillRate() {
        return typesOfHero.magicRefillRate;
    }
    public void setMagicRefillRate(int magicRefillRate) {
        this.typesOfHero.magicRefillRate = magicRefillRate;
    }
    public int getCurrentEnergyPoints() {
        return currentEnergyPoints;
    }
    public void setCurrentEnergyPoints(int currentEnergyPoints) {
        this.currentEnergyPoints = currentEnergyPoints;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public void changeEP(int num) throws NotEnoughEnergyPointsException {
        if (this.currentEnergyPoints + num < 0) {
            throw new NotEnoughEnergyPointsException("Your " + name + id + " hero doesn't have Enough EP to perform this" +
                    " move\ncurrent EP : " + currentEnergyPoints + "\nrequired EP : " + -num + "\nYou need " +
                    (-num - currentEnergyPoints) + " additional EPs.");
        }
        this.currentEnergyPoints += num;
        printOutput(this + "current EP : " + currentEnergyPoints);
    }
    public void changeMagic(int i) throws NotEnoughMagicPointsException {
        if (this.currentMagic + i < 0) {
            throw new NotEnoughMagicPointsException("Your " + name + id + " hero doesn't have Enough MP to perform this" +
                    " move\ncurrent MP : " + currentMagic + "\nrequired MP : " + -i + "\nYou need " +
                    (-i - currentEnergyPoints) + " additional MPs.");
        }
        if (this.currentMagic + i > info.maxMagic) {
            currentMagic = maxHealth;
        } else {
        this.currentMagic += i;
        }
    }
    public void changeMaxMagic(int i) throws NotEnoughMagicPointsException {
        this.info.maxMagic += i;
    }


    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Hero other = (Hero) obj;
        if (!this.name.equals(other.name)) return false;
        return true;
    }


    public void showCurrentItems() {
        String toPrint = this.getName() + " has ";
        for (Item i : inventory.getItems()) {
            toPrint += i.getInfo().getName() + " worth " + i.getCurrentPrice() + " dollars, ";
        }
        if (toPrint.equals(this.getName() + " has ")) {
            printOutput(toPrint + "no items yet!");
        } else {
            printOutput(toPrint);
        }
    }

    @Deprecated
    public void abilityDescription(Ability targetAbility) {
        targetAbility.showDescription();
    }


    @Completed
    public void showHeroDescription() {
        printOutput(info.heroDescription);
    }

    @Completed
    public void showClassDescription() {
        printOutput(info.classDescription);
    }

    @Override
    public void changeHealth(int quantitiy) {
        if (wasAlive() & !willDye(quantitiy)) {
            changeHealthWithInsuranceOfLiving(quantitiy);
            return;
        }

        if (wasAlive() & willDye(quantitiy)) {
            currentHealth = 0;
            boolean isAnyImmortalityLeft = ChildrenOfTime.getInstance().getPlayers().get(0).isAnyImmortalityPotionLeft();
            if (isAnyImmortalityLeft) {
                useImmortalityPotion();
                printOutput(WarriorMessages.getDyingMessageForHero(ChildrenOfTime.getInstance().getPlayers().get(0), this));
            } else {
                isDead = true;
                printOutput(WarriorMessages.getDiedMessageForHero(this));
                printOutput("No Immortality Potions left.");

            }

        }
    }
    @Override
    public String showCurrentTraits() {
        String toPrint = "";
        toPrint = "Health: " + currentHealth + "/" + maxHealth + "\n" +
                "Magic: " + currentMagic + "/" + info.maxMagic + "\n" +
                "Energy points: " + currentEnergyPoints + "\n" +
                "Attack power " + attackPower + "\n";
        for (Map.Entry<String, Ability> entry : abilities.entrySet()) {
            if (entry.getValue().currentLevel == 0) continue;
            toPrint += "Can Cast " + entry.getKey() + " for " + entry.getValue().info.masrafEP + " energy points, " + entry.getValue().info.masrafEP + " magic points and a " + entry.getValue().info.coolDownTime + " turn cooldown in some upgrades\n";
        }
        for (Item item : inventory.getItems()) {
            toPrint += "Can Use " + item.getInfo().getName() + " for " + 0 + " energy points, " + 0 + " magic points and a " + 0 + " turn cooldown\n";
        }
        printOutput(toPrint);
        return toPrint;
    }

    public void useImmortalityPotion() {
        ChildrenOfTime.getInstance().getPlayers().get(0).useImmortalityPotion();
        this.currentHealth = this.maxHealth;
    }

    public void aTurnHasPassed() {
        for (int i = 0; i < inventory.getItems().size(); i++) {
            inventory.getItems().get(i).aTurnHasPassed();
        }
        for (Map.Entry<String, Ability> entry : abilities.entrySet()) {
            abilities.get(entry.getKey()).aTurnHasPassed();

        }

        this.currentEnergyPoints = info.initialEP;

    }


}
