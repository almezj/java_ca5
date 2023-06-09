package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlRacquetDao extends MySqlDao implements RacquetDaoInterface{

    private Connection connection;

    @Override
    public List<Racquet> findAllRacquets() throws DaoException {
        //Function to return a list of all racquets in the database.

        connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Racquet> racquetList = new ArrayList<>();

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "SELECT * FROM RACQUET_SPECIFICATIONS";
            ps = connection.prepareStatement(query);

            //Using a prepared statement.
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int spec_id = resultSet.getInt("SPEC_ID");
                int player_id = resultSet.getInt("PLAYER_ID");
                String brand = resultSet.getString("BRAND");
                String model = resultSet.getString("MODEL");
                int head_size = resultSet.getInt("HEAD_SIZE");
                int weight = resultSet.getInt("WEIGHT");
                String string_pattern = resultSet.getString("STRING_PATTERN");
                Racquet r = new Racquet(spec_id, player_id, brand, model, weight, head_size, string_pattern);
                racquetList.add(r);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllRacquetsResultSet() " + e.getMessage());
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
                throw new DaoException("findAllRacquets() " + e.getMessage());
            }
        }
        return racquetList;
    }

    @Override
    public boolean doesPlayerIdHaveRacquets(int playerId) throws DaoException {
        //Function to check if a player has a racquet

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean hasRacquet = false;

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "SELECT * FROM RACQUET_SPECIFICATIONS WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, playerId);

            //Using a prepared statement
            resultSet = ps.executeQuery();
            if (resultSet.next())
            {
                hasRacquet = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("findPlayerIdRacquetsResultSet() " + e.getMessage());
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
                throw new DaoException("findPlayerIdRacquets() " + e.getMessage());
            }

            return hasRacquet;
        }
    }

    @Override
    public boolean deleteRacquetById(int specId) throws DaoException {
        //Function to delete a racquet by spec id

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "DELETE FROM RACQUET_SPECIFICATIONS WHERE SPEC_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, specId);

            //Using a prepared statement
            resultSet = ps.executeQuery();
            if (resultSet.next())
            {
                deleted = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("deleteRacquetResultSet() " + e.getMessage());
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
                throw new DaoException("deleteRacquet() " + e.getMessage());
            }

            return deleted;
        }
    }

    @Override
    public boolean deletePlayerRacquets(int playerId) throws DaoException {
        //Function to delete a racquet of a player by player id

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try
        {
            //Get connection object
            connection = this.getConnection();

            String query = "DELETE FROM RACQUET_SPECIFICATIONS WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, playerId);

            //Using a prepared statement
            resultSet = ps.executeQuery();
            if (resultSet.next())
            {
                deleted = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("deletePlayerRacquetsResultSet() " + e.getMessage());
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
                throw new DaoException("deletePlayerRacquets() " + e.getMessage());
            }

            return deleted;
        }
    }

    public List<Racquet> findRacquetsByCriteria(String criteria, String criteriaValue) throws DaoException {
        List<Racquet> racquetList = findAllRacquets();
        ArrayList<Racquet> filteredRacquetList = new ArrayList<>(racquetList);

        switch (criteria){
            case "brand":
                for (Racquet r : racquetList){
                    if (!r.getBrand().toLowerCase().contains(criteriaValue)){
                        filteredRacquetList.remove(r);
                    }
                }
                break;
            case "model":
                for (Racquet r : racquetList){
                    if (!r.getModel().toLowerCase().contains(criteriaValue)){
                        filteredRacquetList.remove(r);
                    }
                }
                break;
            case "weight":
                for (Racquet r : racquetList){
                    //If the weight is not within 10 grams of the criteria value, remove it from the list
                    if (r.getWeight() != Integer.parseInt(criteriaValue) - 10 || r.getWeight() != Integer.parseInt(criteriaValue) + 10){
                        filteredRacquetList.remove(r);
                    }
                }
                break;
            case "headsize":
                for (Racquet r : racquetList){
                    if (r.getHead_size() != Integer.parseInt(criteriaValue)){
                        filteredRacquetList.remove(r);
                    }
                }
                break;
            case "stringpattern":
                for (Racquet r : racquetList){
                    if (!r.getString_pattern().toLowerCase().equals(criteriaValue)){
                        filteredRacquetList.remove(r);
                    }
                }
                break;
            case "playerid":
                for (Racquet r : racquetList){
                    if (r.getPlayerId() != Integer.parseInt(criteriaValue)){
                        filteredRacquetList.remove(r);
                    }
                }
                break;
            default:
                System.out.println("Invalid criteria");
                break;
        }

        return filteredRacquetList;
    }

    public void closeConnection() {
        //Function to close the connection to the database

        try
        {
            if (connection != null)
            {
                connection.close();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
