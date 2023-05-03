package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.SQLException;
import java.sql.Date;
import java.util.*;

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

    public static List<Player> findPlayerByFirstName(List<Player> playerList, String fname){

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

    public static List<Player> findPlayerByLastName(List<Player> playerList, String lname){

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

    public static List<Player> findPlayerByCountry(List<Player> playerList, String countryCode){

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


    public static Player findPlayerById(Set<Integer> playerIdCache, int playerID) throws SQLException {

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
