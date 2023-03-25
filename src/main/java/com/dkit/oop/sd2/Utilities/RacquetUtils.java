package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DAOs.MySqlRacquetDao;
import com.dkit.oop.sd2.DAOs.RacquetDaoInterface;
import com.dkit.oop.sd2.Exceptions.DaoException;

public class RacquetUtils {

    static RacquetDaoInterface IRacquetDao = new MySqlRacquetDao();
    public static void playerHasRacquetPrompt(int playerID) throws DaoException {
        if (IRacquetDao.doesPlayerIdHaveRacquets(playerID)) {
            System.out.println("Player " + playerID + " has racquets.");
            System.out.println("Deleting this player will delete all racquets associated with this player.");
        }
    }
}
