package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DAOs.MySqlRacquetDao;
import com.dkit.oop.sd2.DAOs.PlayerDaoInterface;
import com.dkit.oop.sd2.DAOs.RacquetDaoInterface;
import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.Utilities.*;
import com.dkit.oop.sd2.Utilities.Console;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Client
{

    public static void main(String[] args) throws SQLException {
        Client client = new Client();
        client.start();
    }

    public void start() throws SQLException {

        //Initialize variables
        Scanner keyboard = new Scanner(System.in);
        Menu menu = new Menu();
        Console console = new Console();
        Gson gson = new Gson();



        Scanner in = new Scanner(System.in);



        System.out.println("Tennis Player Database Interface");

        try {
            boolean isRunning = true;
            String command = "";
            Socket socket = new Socket("localhost", 50007);

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);
            Scanner socketReader = new Scanner(socket.getInputStream());

            while(isRunning){

                switch (menu.mainMenu()) {
                    case 1:
                        console.clearConsole();
                        switch (menu.playerMainMenu()){
                            case 1:
                                console.clearConsole();
                                System.out.println("All players");
                                command = "FIND%ALL%PLAYERS";
                                break;
                            case 2:
                                console.clearConsole();
                                System.out.println("Enter the country code of the players you want to find");
                                String countryCode = keyboard.nextLine().trim().toUpperCase();
                                command = "FIND%COUNTRY%" + countryCode;
                                break;
                            case 3:
                                console.clearConsole();
                                System.out.println("Enter the first name of the players you want to find");
                                String fname = keyboard.nextLine().trim().toUpperCase();
                                command = "FIND%FIRST_NAME%" + fname;
                                break;
                            case 4:
                                console.clearConsole();
                                System.out.println("Enter the last name of the players you want to find");
                                String lname = keyboard.nextLine().trim().toUpperCase();
                                command = "FIND%LAST_NAME%" + lname;
                                break;
                            case 5:
                                console.clearConsole();
                                System.out.println("Enter the player ID of the player you want to find: ");
                                int playerID = Input.validateInput(Integer.MAX_VALUE);
                                command = "FIND%ID%" + playerID;
                                break;
                            case 6:
                                console.clearConsole();
                                String f_name = "";
                                String l_name = "";
                                String country = "";
                                int points = 0;
                                String birthDateString = "";
                                String birthDate = null;

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
                                command = "ADD%PLAYER%" + f_name + "%" + l_name + "%" + country + "%" + points + "%" + birthDateString;
                                command.toUpperCase();
                                break;
                            case 7:
                                console.clearConsole();
                                System.out.println("Delete a player");
                                int delId = PlayerUtils.deletePlayerPrompt();
                                command = "DELETE%PLAYER%" + delId;
                                break;
                            case 8:
                                console.clearConsole();
                                System.out.println("All players JSON format");
                                command = "FIND%JSON%ALL";
                                break;
                            case 9:
                                console.clearConsole();
                                System.out.println("Find a player by ID");
                                System.out.println("Enter player ID: ");
                                int findId = in.nextInt();
                                command = "FIND%JSON%ID" + findId;
                                break;

                            default:
                                console.clearConsole();
                                break;
                        }

                        break;
                    case 2:
                        console.clearConsole();
                        switch (menu.racquetMainMenu()){
                            case 1:
                                command = "FIND%ALL%RACQUETS";
                                break;
                            case 2:
                                command = "FIND%ALL%RACQUETS%FILTERED";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        console.clearConsole();
                        command = "CLOSE";
                        isRunning = false;
                        break;
                    default:
                        console.clearConsole();
                        break;
                }

                socketWriter.println(command);

                if(socketReader.hasNextLine()){
                    System.out.println(socketReader.nextLine());
                }
            }



            socketWriter.close();

            socketReader.close();

            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}