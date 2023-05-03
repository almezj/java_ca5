package com.dkit.oop.sd2.BusinessObjects;
import com.dkit.oop.sd2.DAOs.MySqlPlayerDao;
import com.dkit.oop.sd2.DAOs.MySqlRacquetDao;
import com.dkit.oop.sd2.DAOs.PlayerDaoInterface;
import com.dkit.oop.sd2.DAOs.RacquetDaoInterface;
import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Utilities.PlayerUtils;
import com.dkit.oop.sd2.Utilities.RacquetUtils;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(50007);

            System.out.println("Server: Listening for connections on port 50007...");

            int clientNumber = 0;


            while (true)
            {
                Socket socket = ss.accept();
                clientNumber++;

                System.out.println("Server: Connection from client number: " + clientNumber + ". ");

                Thread t = new Thread(new ClientHandler(socket, clientNumber));
                t.start();

                System.out.println("Server: Client number: " + clientNumber + ". ");
                System.out.println("Server: Listening...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
    }

    public class ClientHandler implements Runnable
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true);
                this.clientNumber = clientNumber;
                this.socket = clientSocket;

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            System.out.println("Im here");
            String message;
            Gson gson = new Gson();
            PlayerDaoInterface IPlayerDao = new MySqlPlayerDao();
            RacquetDaoInterface IRacquetDao = new MySqlRacquetDao();
            PlayerUtils playerUtils = new PlayerUtils();
            RacquetUtils racquetUtils = new RacquetUtils();

            try
            {
                //Create a cache of all player IDs
                Set<Integer> playerIdCache = new HashSet<>(IPlayerDao.getAllPlayerIds());

                while ((message = socketReader.readLine()) != null)
                {

                    if (message.startsWith("FIND"))
                    {
                        List<Player> players = IPlayerDao.findAllPlayers();
                        List<Racquet> racquets = IRacquetDao.findAllRacquets();

                        String[] tokens = message.split("%");
                        String subcommand = tokens[1];

                        switch(subcommand){
                            case "ALL":
                                if (tokens[2].startsWith("RACQUETS")){
                                    if (tokens[3].startsWith("FILTERED")){
                                        List<Racquet> filteredRacquets = RacquetUtils.findRacquetsBy(racquets);
                                        socketWriter.println(gson.toJson(filteredRacquets));
                                    } else {
                                        socketWriter.println(gson.toJson(racquets));
                                    }
                                } else if (tokens[2].startsWith("PLAYERS")){
                                    socketWriter.println(gson.toJson(players));
                                }
                                else {
                                    socketWriter.println("ERROR: Unrecognised command");
                                }
                                break;
                            case "COUNTRY":
                                List<Player> response = playerUtils.findPlayerByCountry(players, tokens[2]);
                                socketWriter.println(gson.toJson(response));
                                break;
                            case "FIRST_NAME":
                                List<Player> response2 = playerUtils.findPlayerByFirstName(players, tokens[2]);
                                socketWriter.println(gson.toJson(response2));
                                break;
                            case "LAST_NAME":
                                List<Player> response3 = playerUtils.findPlayerByLastName(players, tokens[2]);
                                socketWriter.println(gson.toJson(response3));
                                break;
                            case "ID":
                                if( playerIdCache.isEmpty() ){
                                    socketWriter.println("ERROR: No players in database");
                                }
                                Player response4 = playerUtils.findPlayerById(playerIdCache, Integer.parseInt(tokens[2]));
                                socketWriter.println(gson.toJson(response4));
                                break;
                            case "JSON":
                                String filter = tokens[2];
                                if (filter.startsWith("ALL")){
                                    IPlayerDao.findAllPlayersJson();
                                } else if (filter.startsWith("ID")){
                                    String id = tokens[3];
                                    IPlayerDao.findPlayerByIdJson(id);
                                }

                            default:
                                socketWriter.println("ERROR: Unrecognised command");
                                break;
                        }
                    }
                    else if (message.startsWith("ADD")) {
                        String[] tokens = message.split("%");
                        String subcommand = tokens[1];

                        switch(subcommand){
                            case "PLAYER":
                                Player newPlayer = new Player(tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), tokens[6]);
                                IPlayerDao.addPlayer(newPlayer);
                                socketWriter.println("Player added");
                                socketWriter.println(newPlayer.toString());
                                break;
                            default:
                                socketWriter.println("ERROR: Unrecognised command");
                                break;
                        }

                    }
                    else if (message.startsWith("DELETE")) {
                        String[] tokens = message.split("%");
                        String subcommand = tokens[1];

                        switch(subcommand){
                            case "PLAYER":
                                IPlayerDao.deletePlayer(Integer.parseInt(tokens[2]));
                                socketWriter.println("Player deleted");
                                break;
                            default:
                                socketWriter.println("ERROR: Unrecognised command");
                                break;
                        }

                    }
                    else if (message.startsWith("CLOSE"))
                    {
                        socketWriter.println("Exiting...");
                        break;
                    }
                    else
                    {
                        socketWriter.println("ERROR: Unrecognised command");
                    }
                    playerIdCache = PlayerUtils.reloadPlayerCache();
                }
                socket.close();
            } catch (IOException | SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}