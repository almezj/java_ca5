package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DTOs.Player;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner keyboard = new Scanner(System.in);
    static Input userInput = new Input();
    //This class will contain functions to display menus and get user input
    public static int mainMenu() {
        final int MAX_OPTION = 3;
        System.out.println("Please select an option:");
        System.out.println("1. Player Features");
        System.out.println("2. Racquet Features");
        System.out.println("3. Exit");

        System.out.print("Enter your option: ");

        String option = keyboard.nextLine();

        int validatedOption = userInput.validateInput(option, 3);

        return validatedOption;
    }

    public static int playerMainMenu() {
        final int MAX_OPTION = 9;
        System.out.println("Please select an option:");
        System.out.println("1. Display all players");
        System.out.println("2. Display all players from a country");
        System.out.println("3. Display a player by first name");
        System.out.println("4. Display a player by last name");
        System.out.println("5. Display player by ID");
        System.out.println("6. Add new player");
        System.out.println("7. Delete a player by ID");
        System.out.println("8. Display all racquets of a player");
        System.out.println("9. Back to main menu");

        System.out.print("Enter your option: ");

        String option = keyboard.nextLine();

        int validatedOption = userInput.validateInput(option, MAX_OPTION);

        return validatedOption;
    }

    public static int racquetMainMenu() {
        final int MAX_OPTION = 6;
        System.out.println("Please select an option:");
        System.out.println("1. Display all racquets");
        System.out.println("3. Display a racquets by brand");
        System.out.println("4. Display a racquets by model");
        System.out.println("5. Display racquets of a player");
        System.out.println("6. Add new racquet");
        System.out.println("5. Delete a racquet");
        System.out.println("6. Back to main menu");

        System.out.print("Enter your option: ");

        String option = keyboard.nextLine();

        int validatedOption = userInput.validateInput(option, MAX_OPTION);

        return validatedOption;
    }

    public static int displayAllPlayersAsMenu(List<Player> players) {
        System.out.println("Display all players");
        int num_of_players = 0;

        //display all players and their id
        for (Player player : players) {
            System.out.println(player.getId() + ". " + player.getFirstName() + " " + player.getLastName());
            num_of_players++;
        }

        System.out.println("Please select a player: ");
        String option = keyboard.nextLine();

        int validatedOption = userInput.validateInput(option, num_of_players);

        return validatedOption;
    }
}
