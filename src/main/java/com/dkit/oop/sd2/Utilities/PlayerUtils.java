package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import java.sql.Date;

public class PlayerUtils {

    static Scanner keyboard = new Scanner(System.in);
    static Menu menu = new Menu();
    static MySqlPlayerDao IPlayerDao = new MySqlPlayerDao();


    public static void showPlayers(List<Player> playerList) {
        if (playerList.isEmpty()) {
            System.out.println("There are no players to display.");
        } else {
            System.out.format("%-10s %-20s %-20s %-10s %-15s %-15s \n", "ID", "Name", "Surname", "Country", "Points", "Date Of Birth");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
            for (Player player : playerList) {
                System.out.format("%-10d %-20s %-20s %-10s %-15s %-15s\n", player.getId(), player.getFirstName(),
                        player.getLastName(), player.getCountry(), player.getPoints() + "pts", player.getDateOfBirth());
            }
        }
    }

    public static List<Player> findPlayerByFirstName(List<Player> playerList){
        System.out.println("Enter the first name of the players you want to find");
        String fname = keyboard.nextLine().trim();
        List<Player> filteredPlayers = new ArrayList<>();
        Player p1 = new Player();


        Collections.sort(playerList, p1.PlayerPointsComparator);

        for (Player player : playerList) {
            if (player.getFirstName().equalsIgnoreCase(fname)) {
                System.out.println("Player: " + player.toString());
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }

    public static List<Player> findPlayerByLastName(List<Player> playerList){
        System.out.println("Enter the last name of the players you want to find");
        String lname = keyboard.nextLine().trim();
        List<Player> filteredPlayers = new ArrayList<>();
        Player p1 = new Player();


        Collections.sort(playerList, p1.PlayerPointsComparator);

        for (Player player : playerList){
            if (player.getLastName().equalsIgnoreCase(lname)){
                System.out.println("Player: " + player.toString());
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }

    public static List<Player> findPlayerByCountry(List<Player> playerList){
        //Function to collect the country code and return a list of all players from that country using player comparator

        System.out.println("Enter the country code of the players you want to find");
        String countryCode = keyboard.nextLine().trim();
        List<Player> filteredPlayers = new ArrayList<>();
        Player p1 = new Player();


        Collections.sort(playerList, p1.PlayerPointsComparator);

        for (Player player : playerList){
            if (player.getCountry().equalsIgnoreCase(countryCode)){
                System.out.println("Player: " + player.toString());
                filteredPlayers.add(player);
            }
        }


        return filteredPlayers;
    }


    public static int deletePlayerPrompt() throws DaoException {
        //Function to collect the player ID of the player to be deleted from the user and return the player ID

        System.out.println("Enter the player ID of the player you want to delete: ");
        int playerID = Input.validateInput(Integer.MAX_VALUE);
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
        String birthDateString = "";
        Date birthDate = null;


        System.out.println("Enter the first name: ");
        f_name = keyboard.nextLine().trim();
        System.out.println("Enter the last name: ");
        l_name = keyboard.nextLine().trim();
        System.out.println("Enter the country code (e.g USA): ");
        country = keyboard.nextLine().trim();
        System.out.println("Enter the points: ");
        points = Input.validateInput(Integer.MAX_VALUE);
        System.out.println("Enter the birth date (YYYY-MM-DD): ");
        birthDateString = keyboard.nextLine().trim();
        //TODO: Change player birth date to type Date instead of String

        Player player = new Player(f_name, l_name, country, points, birthDate);
        return player;
    }

    public static Player findPlayerById(Set<Integer> playerIdCache) throws SQLException {
        //Function to collect the player ID of the player to be found from the user and return the player ID

        System.out.println("Enter the player ID of the player you want to find: ");
        int playerID = Input.validateInput(Integer.MAX_VALUE);

        if(playerIdCache.contains(playerID)){
            Player p = IPlayerDao.findPlayerById(playerID);
            System.out.println(p.toString());
            return p;
        }
        else{
            System.out.println("Player " + playerID + " has not been found");
            return null;
        }


    }

    public static List<Player> filterPlayersByBirthMonth(List<Player> playerList) throws DaoException {
        System.out.println("Enter the birth month of the players you want to find");
        String birthMonth = keyboard.nextLine().trim();
        List<Player> filteredPlayers = new ArrayList<>();
        Player p1 = new Player();


        Collections.sort(playerList, p1.PlayerDateOfBirthComparator);

        for (Player player : playerList){
            if (player.getMonthOfBirth() == Integer.parseInt(birthMonth)){
                System.out.println("Player: " + player.toString());
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }

    public static List<Player> filterPlayersByBirthYear(List<Player> playerList) throws DaoException {
        System.out.println("Enter the birth month of the players you want to find");
        String birthYear = keyboard.nextLine().trim();
        List<Player> filteredPlayers = new ArrayList<>();
        Player p1 = new Player();


        Collections.sort(playerList, p1.PlayerDateOfBirthComparator);

        for (Player player : playerList){
            if (player.getYearOfBirth() == Integer.parseInt(birthYear)){
                System.out.println("Player: " + player.toString());
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }

    public static Set<Integer> reloadPlayerCache() throws SQLException {
        //Function to reload the player ID cache
        return IPlayerDao.getAllPlayerIds();
    }
}
