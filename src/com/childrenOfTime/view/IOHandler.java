package com.childrenOfTime.view;

import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.utilities.GUIUtils;

import java.util.Scanner;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class IOHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static void printOutput(String output) {

        GUIUtils.showNotification(output, NotificationType.MESSAGE);
    }


    public static void printOutput(String output, NotificationType notificationType) {

        GUIUtils.showNotification(output, notificationType);
    }


    public static String getInput() {
        String toReturn = scanner.nextLine();
        return toReturn;
    }

}
