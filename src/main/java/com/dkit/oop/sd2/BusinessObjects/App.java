package com.dkit.oop.sd2.BusinessObjects;

/** OOP Feb 2022
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses Data Access Objects (DAOs),
 * Data Transfer Objects (DTOs), and  a DAO Interface to define
 * a contract between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "CreateUsers.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

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
    public static void main(String[] args)
    {
        //Create a DAO for each database table
        PlayerDaoInterface IPlayerDao = new MySqlPlayerDao();
        RacquetDaoInterface IRacquetDao = new MySqlRacquetDao();
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
                                PlayerUtils.findPlayerById(IPlayerDao.findAllPlayers());
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
