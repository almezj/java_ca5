package com.dkit.oop.sd2.DAOs;


import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;
import java.util.Set;

public interface PlayerDaoInterface
{
    public List<Player> findAllPlayers() throws DaoException;

    public Player findPlayerById(int id) throws DaoException;

    public boolean deletePlayer(int id) throws DaoException;

    public Player addPlayer(Player p) throws DaoException;

    public Set<Integer> getAllPlayerIds() throws DaoException;
}

