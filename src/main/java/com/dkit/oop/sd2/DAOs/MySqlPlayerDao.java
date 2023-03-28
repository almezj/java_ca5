package com.dkit.oop.sd2.DAOs;


import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlPlayerDao extends MySqlDao implements PlayerDaoInterface
{

    @Override
    public List<Player> findAllPlayers() throws DaoException {
        //Function to return a list of all players in the database.

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playerList = new ArrayList<>();

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYERS";
            ps = connection.prepareStatement(query);

            //Using a prepared statement
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int player_id = resultSet.getInt("PLAYER_ID");
                String f_name = resultSet.getString("FIRST_NAME");
                String l_name = resultSet.getString("LAST_NAME");
                String country = resultSet.getString("COUNTRY");
                int points = resultSet.getInt("POINTS");
                String birth_date = resultSet.getString("BIRTH_DATE");
                Player p = new Player(player_id, f_name, l_name, country, points, birth_date);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllPlayersResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                //Close the connection
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllPlayers() " + e.getMessage());
            }
        }
        return playerList;
    }

    @Override
    public Player findPlayerById(int id){
        //Function to return a player with a given id.

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Player p = null;

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYERS WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            //Using a prepared statement
            resultSet = ps.executeQuery();
            if (resultSet.next())
            {
                int player_id = resultSet.getInt("PLAYER_ID");
                String f_name = resultSet.getString("FIRST_NAME");
                String l_name = resultSet.getString("LAST_NAME");
                String country = resultSet.getString("COUNTRY");
                int points = resultSet.getInt("POINTS");
                String birth_date = resultSet.getString("BIRTH_DATE");
                p = new Player(player_id, f_name, l_name, country, points, birth_date);
            }
        } catch (SQLException e)
        {
            System.out.println("findPlayerByIdResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                //Close the connection
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                System.out.println("findPlayerById() " + e.getMessage());
            }
        }
        return p;
    }

    @Override
    public boolean deletePlayer(int id){
        //Function to delete a player with a given id.

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean result = false;

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "DELETE FROM PLAYERS WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            //Using a prepared statement
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                result = true;
            }
        } catch (SQLException e)
        {
            System.out.println("deletePlayerResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                //Close the connection
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                System.out.println("deletePlayer() " + e.getMessage());
            }
        }
        return result;
    }

    @Override
    public Player addPlayer(Player p){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Player newP = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "INSERT INTO PLAYERS (FIRST_NAME, LAST_NAME, COUNTRY, POINTS, BIRTH_DATE) VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getCountry());
            ps.setInt(4, p.getPoints());
            ps.setString(5, p.getDateOfBirth());

            //Using a PreparedStatement to execute SQL...
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                newP = p;
            }
        } catch (SQLException e)
        {
            System.out.println("addPlayerResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                System.out.println("addPlayer() " + e.getMessage());
            }
        }
        return newP;
    }
}

