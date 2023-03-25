package com.dkit.oop.sd2.DTOs;

public class Player implements Comparable<Player>{
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private int points;
    private String dateOfBirth;

    public Player(String firstName, String lastName, String country, int points, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.points = points;
        this.dateOfBirth = dateOfBirth;
    }

    public Player(int id, String firstName, String lastName, String country, int points, String dateOfBirth) {
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
        this.dateOfBirth = "";
    }

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    //override equals and compareTo methods
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

}
