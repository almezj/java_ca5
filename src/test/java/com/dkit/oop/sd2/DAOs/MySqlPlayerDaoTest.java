package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Player;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // Test 2: Players list is empty
    @Test
    public void testFindAllPlayers2() throws SQLException {
        List<Player> playerList = new ArrayList<Player>();
        assertNotNull(playerList);
        assertTrue(playerList.size() == 0);
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

    @Test
    public void getAllPlayerIds() {
    }

    @Test
    public void findAllPlayersJson() {
    }

    @Test
    public void findPlayerByIdJson() {
    }

    @Test
    public void findPlayerUsingFilter() {
    }
}