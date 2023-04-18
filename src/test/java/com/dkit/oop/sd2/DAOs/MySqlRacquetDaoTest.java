package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Player;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MySqlRacquetDaoTest {
    private static MySqlRacquetDao dao;

    @BeforeClass
    public static void setUp() throws SQLException {
        dao = new MySqlRacquetDao();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        dao.closeConnection();
    }
}
