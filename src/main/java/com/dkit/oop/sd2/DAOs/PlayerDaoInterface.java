package com.dkit.oop.sd2.DAOs;


import com.dkit.oop.sd2.DTOs.Player;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface PlayerDaoInterface
{
    public List<Player> findAllPlayers() throws SQLException;

    public Player findPlayerById(int id) throws SQLException;

    public boolean deletePlayer(int id) throws SQLException;

    public Player addPlayer(Player p) throws SQLException;

    public Set<Integer> getAllPlayerIds() throws SQLException;

    public String findAllPlayersJson() throws SQLException;

    public String findPlayerByIdJson(String id) throws SQLException;
}

