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

    public static void findRacquetsBy(List<Racquet> racquetList) throws DaoException {
        System.out.println("Choose a search option:");
        System.out.println("1. Search by model");
        System.out.println("2. Search by brand");
        System.out.println("3. Search by head size");
        System.out.println("4. Search by weight");
        System.out.println("5. Search by string pattern");
        System.out.println("6. Search by player ID");
        System.out.println("7. Return to main menu");
        System.out.print("Enter your option: ");

        int option = Input.validateInput(7);
        switch (option) {
            case 1:
                System.out.print("Enter model: ");
                String model = Input.validateString();
                racquetList = IRacquetDao.findRacquetsByCriteria("model",model);
                showRacquets(racquetList);
                break;
            case 2:
                System.out.print("Enter brand: ");
                String brand = Input.validateString();
                racquetList = IRacquetDao.findRacquetsByCriteria("brand", brand);
                showRacquets(racquetList);
                break;
            case 3:
                System.out.print("Enter head size: ");
                int headSize = Input.validateInput(300);
                racquetList = IRacquetDao.findRacquetsByCriteria("headsize", String.valueOf(headSize));
                showRacquets(racquetList);
                break;
            case 4:
                System.out.print("Enter weight: ");
                int weight = Input.validateInput(1000);
                racquetList = IRacquetDao.findRacquetsByCriteria("weight", String.valueOf(weight));
                showRacquets(racquetList);
                break;
            case 5:
                System.out.print("Enter string pattern first value []x?: ");
                int stringPatternFirst = Input.validateInput(20);
                System.out.print("Enter string pattern second value" + stringPatternFirst +"x?: ");
                int stringPatternSecond = Input.validateInput(20);
                String stringPattern = stringPatternFirst + "x" + stringPatternSecond;
                racquetList = IRacquetDao.findRacquetsByCriteria("stringpattern", stringPattern);
                showRacquets(racquetList);
                break;
            case 6:
                System.out.print("Enter player ID: ");
                int playerID = Input.validateInput(Integer.MAX_VALUE);
                racquetList = IRacquetDao.findRacquetsByCriteria("playerid", String.valueOf(playerID));
                showRacquets(racquetList);
                break;
            case 7:
                break;
        }



    }
}
