package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface RacquetDaoInterface {

    public List<Racquet> findAllRacquets() throws DaoException;

    public boolean doesPlayerIdHaveRacquets(int playerId) throws DaoException;

    public boolean deleteRacquetById(int specId) throws DaoException;

    public boolean deletePlayerRacquets(int playerId) throws DaoException;
}
