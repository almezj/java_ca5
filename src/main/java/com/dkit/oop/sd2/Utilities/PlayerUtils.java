package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.dkit.oop.sd2.Utilities.Input;
import com.dkit.oop.sd2.Utilities.RacquetUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PlayerUtils {

    static Scanner keyboard = new Scanner(System.in);
    static MySqlPlayerDao IPlayerDao = new MySqlPlayerDao();


    public static void showPlayers(List<Player> playerList){
        //Function to display all players in the list

        if( playerList.isEmpty() )
            System.out.println("There are no players");
        else {
            for (Player player : playerList)
                System.out.println("Player: " + player.toString());
        }
    }

    public static void findPlayerByFirstName(List<Player> playerList){
        //Function to find and display all players with the given first name
        //TODO: Add functionality to return all players with the same first name

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
        //Function to find and display all players with the given last name
        //TODO: Add functionality to return all players with the same last name

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
        //Function to collect the country code and return the first player object found or null if the player is not found
        //TODO: Add functionality to return all players from a country

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


    public static int deletePlayerPrompt() throws DaoException {
        //Function to collect the player ID of the player to be deleted from the user and return the player ID

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
        //Function to collect the details of a new player from the user and return a Player object

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

    public static void findPlayerById(Set<Integer> playerIdCache) throws DaoException {
        //Function to collect the player ID of the player to be found from the user and return the player ID

        System.out.println("Enter the player ID of the player you want to find: ");
        int playerID = Input.validateInput(keyboard.nextLine(), Integer.MAX_VALUE);

        if(playerIdCache.contains(playerID)){
            Player p = IPlayerDao.findPlayerById(playerID);
            System.out.println(p.toString());
        }
        else{
            System.out.println("Player " + playerID + " has not been found");
        }

    }
}
