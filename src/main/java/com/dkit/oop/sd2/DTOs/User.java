package com.dkit.oop.sd2.DTOs;

/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * The DAO creates and populates a User object (DTO) with data retrieved from
 * the resultSet and passes the User object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<User> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class User
{
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(int userId, String firstName, String lastName, String username, String password)
    {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User( String firstName, String lastName, String username, String password)
    {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" +
                lastName + ", username=" + username + ", password=" + password + '}';
    }

}
