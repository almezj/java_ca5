package com.dkit.oop.sd2.Utilities;

import java.util.Scanner;

public class Input {
    private static final int min = 1;
    private static final Scanner keyboard = new Scanner(System.in);

    public static int validateInput(int max) {
        //Function to validate the user input
        //If the input is not a number, or is not between 1 and max, the user is asked to re-enter
        String input = keyboard.nextLine();
        int inputInt = 0;
        try {
            inputInt = Integer.parseInt(input);
            if (inputInt < min || inputInt > max) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
            return -1;
        }
        return inputInt;
    }

    public static String validateString() {
        //Function to validate the string user input
        String input = keyboard.nextLine().trim().toLowerCase();
        if (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a string");
            return "";
        }
        return input;
    }
}
