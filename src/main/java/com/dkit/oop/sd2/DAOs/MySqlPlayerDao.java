package com.dkit.oop.sd2.DAOs;


import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;


public class MySqlPlayerDao extends MySqlDao implements PlayerDaoInterface {

    private Connection connection;
    private Player createPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int player_id = resultSet.getInt("PLAYER_ID");
        String f_name = resultSet.getString("FIRST_NAME");
        String l_name = resultSet.getString("LAST_NAME");
        String country = resultSet.getString("COUNTRY");
        int points = resultSet.getInt("POINTS");
        Date birth_date = resultSet.getDate("BIRTH_DATE");
        return new Player(player_id, f_name, l_name, country, points, birth_date);
    }

    private ResultSet executeSqlQuery(String query, Object[] values) throws DaoException {
        connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //Connection object
            connection = this.getConnection();

            //Prepared statement
            ps = connection.prepareStatement(query);

            //Set values for any placeholders in the SQL statement
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }

            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            throw new DaoException("executeSqlQuery() " + e.getMessage());
        }

        return resultSet;
    }

    @Override
    public List<Player> findAllPlayers() throws SQLException {
        List<Player> playerList = new ArrayList<>();

        String query = "SELECT * FROM PLAYERS";
        ResultSet resultSet = executeSqlQuery(query, new Object[]{});

        while (resultSet.next()) {
            playerList.add(createPlayerFromResultSet(resultSet));
        }

        if (resultSet != null) {
            resultSet.close();
        }

        return playerList;
    }

    @Override
    public Player findPlayerById(int id) throws SQLException {
        //Function to return a player with a given id.
        Player p = null;

        //If the id is in the database, return the player with that id.
        if (getAllPlayerIds().contains(id)) {
            String query = "SELECT * FROM PLAYERS WHERE PLAYER_ID = ?";

            ResultSet resultSet = executeSqlQuery(query, new Object[]{id});

            if (resultSet.next()) {
                p = createPlayerFromResultSet(resultSet);
            }

            if (resultSet != null) {
                resultSet.close();
            }
            return p;
        }
        //Else return null
        return null;

    }

    @Override
    public boolean deletePlayer(int id) throws SQLException {

        boolean result = false;
        String query = "DELETE FROM PLAYERS WHERE PLAYER_ID = ?";

        ResultSet resultSet = executeSqlQuery(query, new Object[]{id});

        if (resultSet.rowDeleted()) {
            result = true;
        }

        if (resultSet != null) {
            resultSet.close();
        }

        return result;
    }

    @Override
    public Player addPlayer(Player p) throws SQLException {
        //Function to add a player to the database.
        Player newP = null;

        String fname = p.getFirstName();
        String lname = p.getLastName();
        String country = p.getCountry();
        int points = p.getPoints();
        Date birth_date = p.getDateOfBirth();


        String query = "INSERT INTO PLAYERS (FIRST_NAME, LAST_NAME, COUNTRY, POINTS, BIRTH_DATE) VALUES (?, ?, ?, ?, ?)";
        ResultSet resultSet = executeSqlQuery(query, new Object[]{fname, lname, country, points, birth_date});

        if (resultSet.rowInserted()) {
            newP = p;
        }

        if (resultSet != null) {
            resultSet.close();
        }

        return newP;
    }

    @Override
    public Set<Integer> getAllPlayerIds() throws SQLException {
        //Function to return a list of all player ids in the database.
        Set<Integer> playerIds = new HashSet<>();
        String query = "SELECT PLAYER_ID FROM PLAYERS";
        ResultSet resultSet = executeSqlQuery(query, new Object[]{});

        while (resultSet.next()) {
            int player_id = resultSet.getInt("PLAYER_ID");
            playerIds.add(player_id);
        }

        if (resultSet != null) {
            resultSet.close();
        }
        return playerIds;
    }

    @Override
    public String findAllPlayersJson() throws SQLException {
        //Retrieve all players from the database and return them as a JSON string using GSON
        List<Player> playerList = this.findAllPlayers();
        Gson gson = new Gson();
        String json = gson.toJson(playerList);
        if(json.equals("null")){
            json = "No players found";
        }
        return json;
    }

    public String findPlayerByIdJson(int id) throws SQLException {
        //Retrieve a single player from the database and return them as a JSON string using GSON
        Gson gson = new Gson();
        String json = gson.toJson(findPlayerById(id));

        if(json.equals("null")){
            json = "Player not found";
        }
        return json;
    }

    public void findPlayerUsingFilter(Comparator<Player> filter) throws SQLException {
        //Function to return a list of players using a filter.

        List<Player> playerList = this.findAllPlayers();
        playerList.sort(filter);
        for (Player p : playerList) {
            System.out.println(p);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}

