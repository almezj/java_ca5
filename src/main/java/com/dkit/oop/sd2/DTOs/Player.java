package com.dkit.oop.sd2.DTOs;

import java.util.Calendar;
import java.util.Comparator;
import java.sql.Date;

public class Player implements Comparable<Player>{
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private int points;
    private Date dateOfBirth;


    //Constructors
    public Player(String firstName, String lastName, String country, int points, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.points = points;
        this.dateOfBirth = dateOfBirth;
    }

    public Player(int id, String firstName, String lastName, String country, int points, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.points = points;
        this.dateOfBirth = dateOfBirth;
    }

    public Player() {
        this.firstName = "";
        this.lastName = "";
        this.country = "";
        this.points = 0;
        this.dateOfBirth = null;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getMonthOfBirth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        return cal.get(Calendar.MONTH);
    }

    public int getYearOfBirth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        return cal.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return
                id + ", " +
                firstName + ", " +
                lastName + ", " +
                country + ", " +
                "Points: " + points + ", " +
                dateOfBirth;
    }

    //Override equals and compareTo methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if ((points != player.points) &&
                (firstName != player.firstName) &&
                (lastName != player.lastName) &&
                (country != player.country) &&
                (dateOfBirth != player.dateOfBirth)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Player p) {
        if (this.points > p.points) {
            return 1;
        } else if (this.points < p.points) {
            return -1;
        } else {
            return 0;
        }
    }

    //Comparators
    public static Comparator<Player> PlayerPointsComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getPoints() - p2.getPoints();
        }
    };

    public static Comparator<Player> PlayerFirstNameComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getFirstName().compareTo(p2.getFirstName());
        }
    };

    public static Comparator<Player> PlayerLastNameComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    };

    public static Comparator<Player> PlayerCountryComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getCountry().compareTo(p2.getCountry());
        }
    };

    public static Comparator<Player> PlayerDateOfBirthComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getDateOfBirth().compareTo(p2.getDateOfBirth());
        }
    };


}
