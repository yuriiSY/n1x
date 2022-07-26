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

    public static boolean userAction(final Commands[] values) {
        try {
            int userCommand = -1;
            do {
                for (int i = 0; i < values.length; i++) {
                    System.out.printf("%d) %s%n", i, values[i].getName());
                }
                int input = Integer.parseInt(READER.readLine());
                if (input >= 0 && input < values.length) {
                    userCommand = input;
                }
            } while (userCommand == -1);
            final Command command = values[userCommand].getCommand();
            if (command == null) {
                return true;
            } else {
                command.execute();
                return false;
            }
        }catch (IOException | NumberFormatException e) {
            System.out.println("Input is not valid");
        }
        return false;
    }

    private static int getUserInput(List<String> names, int length) {
        try {
            if (length>0) {
                System.out.println("Please enter number between 0 and " + length);
            } else {
                System.out.println("Which one");
            }
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
}
