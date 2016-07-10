package com.childrenOfTime.cgd;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.utilities.GUIUtils;

import java.awt.*;
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
    public static Map<String,Image> textures;

    public static String currentUserDataPath;
    public static String currentUserCGDataPath;


    public static ArrayList<Effect> currentUserCustomEffects = new ArrayList<>();


    static {
        texturePackPath = "src/texture_pack/default/";
        textures = new HashMap<>();
        loadTexturePack();
    }



    public static void loadTexturePack() {
        GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "battle.png");
        textures.put("battle", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "battle.png"));
        textures.put("boss", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "boss.png"));
        textures.put("door_down_up", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "door_down_up.png"));
        textures.put("door_up_down", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "door_up_down.png"));
        textures.put("door_left_right", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "door_left_right.png"));
        textures.put("door_right_left", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "door_right_left.png"));
        textures.put("ground1", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "ground1.png"));
        textures.put("ground2", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "ground2.png"));
        textures.put("ground3", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "ground3.png"));
        textures.put("store", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "store.png"));
        textures.put("story", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "story.png"));
        textures.put("upgrade", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "upgrade.png"));
        textures.put("wall", GameEngine.DEFAULT_TOOLKIT.getImage(texturePackPath + "wall.png"));
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
        GUIUtils.deserializeUserFiles();
    }

    public static void initializeCurrentUser() throws IOException {
        currentUserDataPath = "src/user_data/" + currentUser.getUserName() + "/";
        currentUserCGDataPath = currentUserDataPath + "custom_game/";
        new File(currentUserDataPath).mkdir();
        new File(currentUserCGDataPath).mkdir();
        new File(currentUserCGDataPath + "abilities" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "battles" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "currentUserCustomEffects" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "items" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "scenarios" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "warriorClasses" + ".dat").createNewFile();
        new File(currentUserCGDataPath + "warriors" + ".dat").createNewFile();
    }
}
