package com.dkit.oop.sd2.BusinessObjects;


import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DAOs.MySqlRacquetDao;
import com.dkit.oop.sd2.DAOs.PlayerDaoInterface;
import com.dkit.oop.sd2.DAOs.RacquetDaoInterface;
import com.dkit.oop.sd2.DTOs.Player;
import com.google.gson.Gson;

import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.SQLException;
import java.util.*;

import com.dkit.oop.sd2.Utilities.PlayerUtils;
import com.dkit.oop.sd2.Utilities.*;

import static java.lang.Thread.sleep;

public class App
{
    private static Scanner keyboard = new Scanner(System.in);
    static Menu menu = new Menu();
    static Console console = new Console();
    public static void main(String[] args) throws SQLException {

        //Create a GSON object
        Gson gson = new Gson();

        //Create a DAO for each database table
        PlayerDaoInterface IPlayerDao = new MySqlPlayerDao();
        RacquetDaoInterface IRacquetDao = new MySqlRacquetDao();

        //Create a cache of all player IDs
        Set<Integer> playerIdCache = new HashSet<>(IPlayerDao.getAllPlayerIds());


        System.out.println("Tennis Player Database Interface");
        Input input = new Input();
        try
        {
            boolean isRunning = true;

            //Main menu loop
            while(isRunning){

                /*
                * Feature 7
                * Print out JSON string of all players, uncomment to see JSON string
                * Returns "No players found" if no players are found
                * Note: This feature is not in the menu, uncomment to see JSON string
                */
                //System.out.println(IPlayerDao.findAllPlayersJson());

                /*
                * Feature 8
                * Print out JSON string of a player found by ID
                * Returns "Player not found" if player is not found
                * Note: This feature is not in the menu, uncomment to see JSON string
                */
                //String jsonID = gson.toJson(1);
                //System.out.println(IPlayerDao.findPlayerByIdJson(jsonID));



                switch (menu.mainMenu()) {
                    case 1:
                        console.clearConsole();
                        switch (menu.playerMainMenu()){
                            case 1:
                                console.clearConsole();
                                System.out.println("All players");
                                PlayerUtils.showPlayers(IPlayerDao.findAllPlayers());
                                console.pressEnterToContinue();
                                break;
                            case 2:
                                console.clearConsole();
                                PlayerUtils.findPlayerByCountry(IPlayerDao.findAllPlayers());
                                console.pressEnterToContinue();
                                break;
                            case 3:
                                console.clearConsole();
                                PlayerUtils.findPlayerByFirstName(IPlayerDao.findAllPlayers());
                                console.pressEnterToContinue();
                                break;
                            case 4:
                                console.clearConsole();
                                PlayerUtils.findPlayerByLastName(IPlayerDao.findAllPlayers());
                                console.pressEnterToContinue();
                                break;
                            case 5:
                                console.clearConsole();
                                if( playerIdCache.isEmpty() ){
                                    System.out.println("There are no players");
                                }
                                else {
                                    PlayerUtils.findPlayerById(playerIdCache);
                                }
                                console.pressEnterToContinue();
                                break;
                            case 6:
                                console.clearConsole();
                                Player newPlayer = PlayerUtils.addNewPlayerPrompt();
                                IPlayerDao.addPlayer(newPlayer);
                                break;
                            case 7:
                                console.clearConsole();
                                System.out.println("Delete a player");
                                IPlayerDao.deletePlayer(PlayerUtils.deletePlayerPrompt());
                                console.pressEnterToContinue();
                                break;
                            default:
                                console.clearConsole();
                                break;
                        }
                        playerIdCache = PlayerUtils.reloadPlayerCache();
                        break;
                    case 2:
                        console.clearConsole();
                        switch (menu.racquetMainMenu()){
                            case 1:
                                console.clearConsole();
                                RacquetUtils.showRacquets(IRacquetDao.findAllRacquets());
                                console.pressEnterToContinue();
                                break;
                            case 2:
                                console.clearConsole();
                                //TODO: Display all racquets by brand
                                console.pressEnterToContinue();
                                break;
                            case 3:
                                console.clearConsole();
                                //TODO: Display all racquets by model
                                console.pressEnterToContinue();
                                break;
                            case 4:
                                console.clearConsole();
                                //TODO: Display all racquets of a player
                                console.pressEnterToContinue();
                                break;
                            case 5:
                                console.clearConsole();
                                //TODO: Add new racquet
                                console.pressEnterToContinue();
                                break;
                            case 6:
                                console.clearConsole();
                                //TODO: Delete a racquet
                                break;
                            case 7:
                                console.clearConsole();
                                //TODO: Back to main menu
                                console.pressEnterToContinue();
                                break;
                            default:
                                console.clearConsole();
                                break;
                        }
                        console.pressEnterToContinue();
                        break;
                    case 3:
                        console.clearConsole();
                        System.out.println("Thank you for using the Tennis Player Database Interface");
                        System.out.println("Exiting...");
                        Thread.sleep(1000);
                        isRunning = false;
                        break;
                    default:
                        console.clearConsole();
                        break;
                }
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
