package com.dkit.oop.sd2;

import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.dkit.oop.sd2.Utilities.Input;
import com.dkit.oop.sd2.Utilities.RacquetUtils;

import java.util.List;
import java.util.Scanner;

public class PlayerUtils {

    static Scanner keyboard = new Scanner(System.in);

    public static void showPlayers(List<Player> playerList){
        if( playerList.isEmpty() )
            System.out.println("There are no players");
        else {
            for (Player player : playerList)
                System.out.println("Player: " + player.toString());
        }
    }

    public static void findPlayerByFirstName(List<Player> playerList){
        System.out.println("Enter the first name of the player you want to find");
        String firstName = keyboard.nextLine().trim();

        if( playerList.isEmpty() )
            System.out.println("There are no players");
        else {
            for (Player player : playerList)
                if (player.getFirstName().equalsIgnoreCase(firstName))
                    System.out.println("Player: " + player.toString());
        }
    }

    public static void findPlayerByLastName(List<Player> playerList){
        System.out.println("Enter the last name of the player you want to find");
        String lastName = keyboard.nextLine().trim();

        if( playerList.isEmpty() )
            System.out.println("There are no players");
        else {
            for (Player player : playerList)
                if (player.getLastName().equalsIgnoreCase(lastName))
                    System.out.println("Player: " + player.toString());
        }
    }

    public static Player findPlayerByCountry(List<Player> playerList){
        System.out.println("Enter the country code of the player you want to find (e.g. USA): ");
        String country = keyboard.nextLine().trim();

        if( playerList.isEmpty() )
            System.out.println("There are no players: ");
        else {
            for (Player player : playerList){
                if (player.getCountry().equalsIgnoreCase(country)){
                    System.out.println("Player: " + player.toString());
                    return player;
                }
            }

        }
        return null;
    }

    public static Player findPlayerById(List<Player> playerList){
        System.out.println("Enter the player ID of the player you want to find: ");
        int playerID = Input.validateInput(keyboard.nextLine(), 1000000);

        if( playerList.isEmpty() )
            System.out.println("There are no players");
        else {
            for (Player player : playerList){
                if (player.getId() == playerID)
                    System.out.println("Player: " + player.toString());
                    return player;
            }
        }
        return null;
    }

    public static int deletePlayerPrompt() throws DaoException {
        System.out.println("Enter the player ID of the player you want to delete: ");
        int playerID = Input.validateInput(keyboard.nextLine(), 1000000);
        RacquetUtils.playerHasRacquetPrompt(playerID);
        System.out.println("Are you sure you want to delete player " + playerID + "? (Y/N)");
        String answer = keyboard.nextLine().trim();
        if(answer.equalsIgnoreCase("Y")){
            System.out.println("Player " + playerID + " has been deleted");
        }
        else{
            System.out.println("Player " + playerID + " has not been deleted");
        }

        return playerID;
    }

    public static Player addNewPlayerPrompt() throws DaoException{
        String f_name = "";
        String l_name = "";
        String country = "";
        int points = 0;
        String birthDate = "";


        System.out.println("Enter the first name: ");
        f_name = keyboard.nextLine().trim();
        System.out.println("Enter the last name: ");
        l_name = keyboard.nextLine().trim();
        System.out.println("Enter the country code (e.g USA): ");
        country = keyboard.nextLine().trim();
        System.out.println("Enter the points: ");
        points = Input.validateInput(keyboard.nextLine(), 1000000);
        System.out.println("Enter the birth date (YYYY-MM-DD): ");
        birthDate = keyboard.nextLine().trim();

        Player player = new Player(f_name, l_name, country, points, birthDate);
        return player;
    }
}
