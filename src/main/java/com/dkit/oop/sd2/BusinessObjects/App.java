package com.dkit.oop.sd2.BusinessObjects;


import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DAOs.MySqlRacquetDao;
import com.dkit.oop.sd2.DAOs.PlayerDaoInterface;
import com.dkit.oop.sd2.DAOs.RacquetDaoInterface;
import com.dkit.oop.sd2.DTOs.Player;

import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.*;

import com.dkit.oop.sd2.Utilities.PlayerUtils;
import com.dkit.oop.sd2.Utilities.*;

import static java.lang.Thread.sleep;

public class App
{
    private static Scanner keyboard = new Scanner(System.in);
    static Menu menu = new Menu();
    static Console console = new Console();
    public static void main(String[] args) throws DaoException {

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
                        break;
                    case 2:
                        console.clearConsole();
                        menu.racquetMainMenu();
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
        }
    }
}
