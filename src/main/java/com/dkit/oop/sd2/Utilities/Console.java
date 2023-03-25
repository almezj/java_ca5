package com.dkit.oop.sd2.Utilities;

public class Console {

    public void pressEnterToContinue() {
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
        clearConsole();
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
