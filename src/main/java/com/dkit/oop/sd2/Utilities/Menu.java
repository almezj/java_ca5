package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DTOs.Player;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner keyboard = new Scanner(System.in);
    static Input userInput = new Input();
    public static int mainMenu() {
        //Main menu for the program

        final int MAX_OPTION = 3;
        System.out.println("Please select an option:");
        System.out.println("1. Player Features");
        System.out.println("2. Racquet Features");
        System.out.println("3. Exit");

        System.out.print("Enter your option: ");

        return userInput.validateInput(3);
    }

    public static int playerMainMenu() {
        //Main menu for player features

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

        return userInput.validateInput(MAX_OPTION);
    }

    public static int racquetMainMenu() {
        //Main menu for racquet features

        final int MAX_OPTION = 7;
        System.out.println("Please select an option:");
        System.out.println("1. Display all racquets");
        System.out.println("2. Display all racquets by criteria");
        System.out.println("5. Back to main menu");

        System.out.print("Enter your option: ");

        return userInput.validateInput(MAX_OPTION);
    }

    public static int displayAllPlayersAsMenu(List<Player> players) {
        //Function to display all players as a menu using enumeration
        //Returns the id of the player selected

        System.out.println("Display all players");
        int num_of_players = 0;

        //display all players and their id
        for (Player player : players) {
            System.out.println(player.getId() + ". " + player.getFirstName() + " " + player.getLastName());
            num_of_players++;
        }

        System.out.println("Please select a player: ");

        return userInput.validateInput(num_of_players);
    }

    public static int filterMenu() {
        //Main menu for the program

        final int MAX_OPTION = 3;
        System.out.println("Please select an option:");
        System.out.println("1. Filter by country");
        System.out.println("2. Filter by first name");
        System.out.println("3. Filter by last name");
        System.out.println("4. Back to main menu");

        System.out.print("Enter your option: ");

        return userInput.validateInput( 4);
    }
}
