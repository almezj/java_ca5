package com.dkit.oop.sd2.Utilities;

import com.dkit.oop.sd2.DAOs.MySqlRacquetDao;
import com.dkit.oop.sd2.DAOs.RacquetDaoInterface;
import com.dkit.oop.sd2.DTOs.Player;
import com.dkit.oop.sd2.DTOs.Racquet;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public class RacquetUtils {

    static RacquetDaoInterface IRacquetDao = new MySqlRacquetDao();
    public static void playerHasRacquetPrompt(int playerID) throws DaoException {
        //Function that displays a warning prompt if the player has racquets

        if (IRacquetDao.doesPlayerIdHaveRacquets(playerID)) {
            System.out.println("Player " + playerID + " has racquets.");
            System.out.println("Deleting this player will delete all racquets associated with this player.");
        }
    }

    public static void showRacquets(List<Racquet> racquetList) {
        if (racquetList.isEmpty()) {
            System.out.println("There are no racquets to display.");
        } else {
            System.out.format("%-10s %-30s %-20s %-15s %-15s %-15s %-15s\n", "ID", "Model", "Brand", "Head Size", "Weight", "String Pattern", "Used by Player");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
            for (Racquet racquet : racquetList) {
                System.out.format("%-10d %-30s %-20s %-15s %-15s %-15s %-15s\n", racquet.getSpec_id(), racquet.getModel(),
                        racquet.getBrand(), racquet.getHead_size() + "in2",racquet.getWeight() + "g", racquet.getString_pattern(), racquet.getPlayerId());
            }
        }
    }
}
