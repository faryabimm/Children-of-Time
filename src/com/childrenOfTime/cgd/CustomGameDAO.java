package com.childrenOfTime.cgd;

import com.childrenOfTime.gui.customizedElements.Scenario;
import com.childrenOfTime.model.*;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.model.Warriors.HeroClass;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammadmahdi on 5/8/16.
 * custom game DBC
 */

public class CustomGameDAO {
    public static String texturePackPath;
    private static User currentUser = null;
    public static Map<String, ImageIcon> textures;

    public static String currentUserDataPath;
    public static String currentUserCGDataPath;


    public static ArrayList<Effect> currentUserCustomEffects = new ArrayList<>();
    public static ArrayList<Ability> currentUserCustomAbilities = new ArrayList<>();
    public static ArrayList<Item> currentUserCustomItems = new ArrayList<>();
    public static ArrayList<Battle> currentUserCustomBattles = new ArrayList<>();
    public static ArrayList<Scenario> currentUserCustomScenarios = new ArrayList<>();
    public static ArrayList<Warrior> currentUserCustomWarriors = new ArrayList<>();
    public static ArrayList<HeroClass> currentUserCustomWarriorClasses = new ArrayList<>();
    public static ArrayList<Store> currentUserCustomStores = new ArrayList<>();



    static {
        texturePackPath = "src/texture_pack/default/";
        textures = new HashMap<>();
        loadTexturePack();
    }



    public static void loadTexturePack() {
        textures.put("battle", GUIUtils.getIConByFilePath(texturePackPath + "battle.png"));
        textures.put("boss", GUIUtils.getIConByFilePath(texturePackPath + "boss.png"));
        textures.put("door_down_up", GUIUtils.getIConByFilePath(texturePackPath + "door_down_up.png"));
        textures.put("door_up_down", GUIUtils.getIConByFilePath(texturePackPath + "door_up_down.png"));
        textures.put("door_left_right", GUIUtils.getIConByFilePath(texturePackPath + "door_left_right.png"));
        textures.put("door_right_left", GUIUtils.getIConByFilePath(texturePackPath + "door_right_left.png"));
        textures.put("ground1", GUIUtils.getIConByFilePath(texturePackPath + "ground1.png"));
        textures.put("ground2", GUIUtils.getIConByFilePath(texturePackPath + "ground2.png"));
        textures.put("ground3", GUIUtils.getIConByFilePath(texturePackPath + "ground3.png"));
        textures.put("store", GUIUtils.getIConByFilePath(texturePackPath + "store.png"));
        textures.put("story", GUIUtils.getIConByFilePath(texturePackPath + "story.png"));
        textures.put("upgrade", GUIUtils.getIConByFilePath(texturePackPath + "upgrade.png"));
        textures.put("wall", GUIUtils.getIConByFilePath(texturePackPath + "wall.png"));


    }

    CustomGameDAO instance;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CustomGameDAO.currentUser = currentUser;
    }

    CustomGameDAO getInstance() {
        if (instance == null) instance = new CustomGameDAO();

        return instance;
    }

    private CustomGameDAO() {

    }

    public static void loadCurrentUserData() {
        currentUserDataPath = "src/user_data/" + currentUser.getUserName() + "/";
        currentUserCGDataPath = currentUserDataPath + "custom_game/";
        GUIUtils.deserializeUserFiles();
    }

    public static void initializeCurrentUser() throws IOException {
        currentUserDataPath = "src/user_data/" + currentUser.getUserName() + "/";
        currentUserCGDataPath = currentUserDataPath + "custom_game/";
        new File(currentUserDataPath).mkdir();
        new File(currentUserCGDataPath).mkdir();
        new File(currentUserCGDataPath + "abilities" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "battles" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "effects" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "items" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "scenarios" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "warriorClasses" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "warriors" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "stores" + ".dat").createNewFile();

        currentUserCustomEffects = new ArrayList<>();
        currentUserCustomAbilities = new ArrayList<>();
        currentUserCustomItems = new ArrayList<>();
        currentUserCustomBattles = new ArrayList<>();
        currentUserCustomScenarios = new ArrayList<>();
        currentUserCustomWarriors = new ArrayList<>();
        currentUserCustomWarriorClasses = new ArrayList<>();
        currentUserCustomStores = new ArrayList<>();

        GUIUtils.serializeUserObject(currentUserCustomEffects,"effects");
        GUIUtils.serializeUserObject(currentUserCustomEffects, "abilities");
        GUIUtils.serializeUserObject(currentUserCustomItems, "items");
        GUIUtils.serializeUserObject(currentUserCustomBattles, "battles");
        GUIUtils.serializeUserObject(currentUserCustomScenarios, "scenarios");
        GUIUtils.serializeUserObject(currentUserCustomWarriors, "warriors");
        GUIUtils.serializeUserObject(currentUserCustomWarriorClasses, "warriorClasses");
        GUIUtils.serializeUserObject(currentUserCustomStores, "stores");

    }
}
