package com.childrenOfTime.view;

import java.util.Scanner;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class IOHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static void printOutput(String output) {

        System.out.println(output);
    }

    public static String getInput() {
        String toReturn = scanner.nextLine();
        return toReturn;
    }

}
