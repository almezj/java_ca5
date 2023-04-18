package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Player;
import com.google.gson.Gson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MySqlPlayerDaoTest {

    private static MySqlPlayerDao dao;

    @BeforeClass
    public static void setUp() throws SQLException {
        dao = new MySqlPlayerDao();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        dao.closeConnection();
    }

    // ----------------------- Tests for findAllPlayers() -----------------------

    // Test 1: Players list is not empty
    @Test
    public void testFindAllPlayers1() throws SQLException {
        List<Player> playerList = dao.findAllPlayers();
        assertNotNull(playerList);
        assertTrue(playerList.size() > 0);
    }

    // ----------------------- Tests for findPlayerById() -----------------------

    // Test 1: Player exists
    @Test
    public void testFindPlayerById1() throws SQLException {
        int id = 2;
        Player p = dao.findPlayerById(id);
        assertNotNull(p);
        assertEquals(id, p.getId());
    }

    // Test 2: Player does not exist
    @Test
    public void testFindPlayerById2() throws SQLException {
        int id = 1;
        Player p = dao.findPlayerById(id);
        assertNull(p);
        assertEquals(id, p.getId());
    }

    // ----------------------- Tests for deletePlayer() -----------------------

    // Test 1: Player exists
    @Test
    public void testDeletePlayer1() throws SQLException {
        int id = 2;
        boolean result = dao.deletePlayer(id);
        assertTrue(result);
        assertNull(dao.findPlayerById(id));
    }

    // Test 2: Player does not exist
    @Test
    public void testDeletePlayer2() throws SQLException {
        int id = 1;
        boolean result = dao.deletePlayer(id);
        assertFalse(result);
        assertNull(dao.findPlayerById(id));
    }

    // ----------------------- Tests for addPlayer() -----------------------

    // Test 1: Player added successfully
    @Test
    public void addPlayer1() throws SQLException {
        Player p = new Player("John", "Doe", "Ireland", 100, new java.sql.Date(System.currentTimeMillis()));
        Player newP = dao.addPlayer(p);
        assertNotNull(newP);
        assertEquals(p.getFirstName(), newP.getFirstName());
        assertEquals(p.getLastName(), newP.getLastName());
        assertEquals(p.getCountry(), newP.getCountry());
        assertEquals(p.getPoints(), newP.getPoints());
        assertEquals(p.getDateOfBirth(), newP.getDateOfBirth());
        dao.deletePlayer(newP.getId());
    }

    // ----------------------- Tests for getAllPlayerIds() -----------------------

    // Test 1: Players set is not empty
    @Test
    public void getAllPlayerIds() throws SQLException {
        Set<Integer> playerIds = dao.getAllPlayerIds();
        assertNotNull(playerIds);
        assertTrue(playerIds.size() > 0);
    }

    // ----------------------- Tests for findAllPlayersJson() -----------------------

    // Test 1: Players JSON is not empty
    @Test
    public void findAllPlayersJson() throws SQLException {
        String json = dao.findAllPlayersJson();
        assertNotNull(json);
        assertTrue(json.length() > 0);
    }

    // ----------------------- Tests for findPlayerByIdJson() -----------------------

    // Test 1: Player ID Exists
    @Test
    public void findPlayerByIdJson1() throws SQLException {
        Gson gson = new Gson();
        int id = 2;
        String jsonID = gson.toJson(id);
        String pJSON = dao.findPlayerByIdJson(jsonID);
        assertNotNull(pJSON);
        assertTrue(pJSON.length() > 0);
    }

    // Test 2: Player ID does not exist
    @Test
    public void findPlayerByIdJson2() throws SQLException {
        Gson gson = new Gson();
        int id = 1;
        String jsonID = gson.toJson(id);
        String pJSON = dao.findPlayerByIdJson(jsonID);
        assertNotNull(pJSON);
        assertTrue(pJSON == "Player not found");
    }

    // ----------------------- Tests for findPlayerUsingFilter() -----------------------

    // Test 1: Filter by country
    @Test
    public void findSortedPlayersBy1() throws SQLException {
        Comparator<Player> comparator = Comparator.comparing(Player::getCountry);
        List<Player> playerList = dao.findAllPlayers();
        List<Player> sortedPlayerList = dao.findSortedPlayersBy(comparator);
        assertNotNull(sortedPlayerList);
        assertTrue(sortedPlayerList.size() > 0);
        assertEquals(playerList.size(), sortedPlayerList.size());
    }
}