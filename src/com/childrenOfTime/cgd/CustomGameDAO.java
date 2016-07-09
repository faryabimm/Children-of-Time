package com.childrenOfTime.cgd;

import com.childrenOfTime.controller.GameEngine;

import java.awt.*;
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

    }

    public static void initializeCurrentUser() {

    }
}
