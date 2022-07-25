package com.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserInputUtil {

    private UserInputUtil() {
    }

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static int getUserInput(int length, List<String> names) {
        int userType = -1;
        do {
            userType = getUserInput(names, length);
        } while (userType == -1);
        return userType;
    }

    public static int getUserInput(List<String> names) {
        int userType = -1;
        do {
            userType = getUserInput1(names);
        } while (userType == -1);
        return userType;
    }

    private static int getUserInput(List<String> names, int length) {
        try {
            System.out.println("Please enter number between 0 and " + length);
            for (int i = 0; i < length; i++) {
                System.out.printf("%d) %s%n", i, names.get(i));
            }
            int input = Integer.parseInt(READER.readLine());
            if (input >= 0 && input < length) {
                return input;
            }
        }    catch (IOException | NumberFormatException e) {
            System.out.println("Input is not valid");
        }
        return -1;
    }


    private static int getUserInput1(List<String> names) {
        try {
            System.out.println("Please choose product which you want to update");
            int input = Integer.parseInt(READER.readLine());
            if (input >= 0 && input < names.size()) {
                return input;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Input is not valid");
        }
        return -1;
    }
}
