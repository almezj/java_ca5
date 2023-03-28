package com.dkit.oop.sd2.Utilities;

public class Input {
    private static final int min = 1;

    public static int validateInput(String input, int max) {
        //Function to validate the user input
        //If the input is not a number, or is not between 1 and max, the user is asked to re-enter

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
}
