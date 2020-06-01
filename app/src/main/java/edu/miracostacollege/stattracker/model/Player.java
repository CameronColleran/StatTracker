/**
 * Player.java: Class which represents a player and all corresponding data
 *
 * @author Cameron Colleran
 * @version 1.0
 */
package edu.miracostacollege.stattracker.model;

import android.net.Uri;

import java.util.List;
import java.util.Objects;

public class Player
{
    /** Instance variables */
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String height;
    private double weight;
    private Uri uri;
    private List<Game> gamesList;

    /**
     * Empty Constructor
     */
    public Player()
    {

    }

    /**
     * Overloaded Constructor
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param age
     * @param height
     * @param weight
     * @param uri
     * @param gamesList
     */
    public Player(long id, String firstName, String lastName, int age, String height, double weight, Uri uri, List<Game> gamesList)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.uri = uri;
        this.gamesList = gamesList;
    }

    /**
     * Overloaded Constructor
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param age
     * @param height
     * @param weight
     * @param uri
     */
    public Player(long id, String firstName, String lastName, int age, String height, double weight, Uri uri)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.uri = uri;
    }

    /**
     * Overloaded Constructor
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param height
     * @param weight
     * @param uri
     * @param gamesList
     */
    public Player(String firstName, String lastName, int age, String height, double weight, Uri uri, List<Game> gamesList)
    {
        this(-1, firstName, lastName, age, height, weight, uri, gamesList);
    }

    /**
     * Overloaded Constructor
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param height
     * @param weight
     * @param uri
     */
    public Player(String firstName, String lastName, int age, String height, double weight, Uri uri)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.uri = uri;
    }

    /**
     * Getter for id
     *
     * @return id
     */
    public long getId()
    {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Getter for First Name
     *
     * @return first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Setter for First Name
     *
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Getter for Last Name
     *
     * @return last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Setter for Last Name
     *
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Getter for age
     *
     * @return age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Setter for age
     *
     * @param age
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Getter for height
     *
     * @return height
     */
    public String getHeight()
    {
        return height;
    }

    /**
     * Setter for height
     *
     * @param height
     */
    public void setHeight(String height)
    {
        this.height = height;
    }

    /**
     * Getter for weight
     *
     * @return weight
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Setter for weight
     *
     * @param weight
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    /**
     * Getter for uri
     *
     * @return uri
     */
    public Uri getUri()
    {
        return uri;
    }

    /**
     * Setter for uri
     *
     * @param uri
     */
    public void setUri(Uri uri)
    {
        this.uri = uri;
    }

    /**
     * Getter for games list
     *
     * @return games list
     */
    public List<Game> getGamesList()
    {
        return gamesList;
    }

    /**
     * Setter for games list
     *
     * @param gamesList
     */
    public void setGamesList(List<Game> gamesList)
    {
        this.gamesList = gamesList;
    }


    /**
     * Method calculates era
     *
     * @return era
     */
    public double calculateEra()
    {
        double totalInnings, totalEarnedRuns, era;
        totalInnings = 0;
        totalEarnedRuns = 0;
        era = 0;

        for (Game g: gamesList)
        {
            totalInnings += g.getInningsPitched();
            totalEarnedRuns += g.getEarnedRuns();
        }

        era = 9 * (totalEarnedRuns / totalInnings);
        return era;
    }

    /**
     * Method which gets total strikeouts
     *
     * @return total strikeouts
     */
    public int getTotalStrikeouts()
    {
        int totalStrikeouts = 0;

        for (Game g: gamesList)
        {
            totalStrikeouts += g.getStrikeouts();
        }

        return totalStrikeouts;
    }

    /**
     * Method which calculates whip
     *
     * @return whip
     */
    public double calculateWhip()
    {
        double walks = 0;
        double hits = 0;
        double inningsPitched = 0;
        double whip = 0;

        for (Game g: gamesList)
        {
            walks += g.getWalks();
            hits += g.getHits();
            inningsPitched += g.getInningsPitched();
        }

        whip = (walks + hits) / inningsPitched;

        return whip;
    }

    /**
     * Method which gets the win loss record
     *
     * @return the win loss record
     */
    public String getWinLoss()
    {
        int wins = 0;
        int loss = 0;

        for (Game g: gamesList)
        {
            if (g.getGameDecision() == 1)
            {
                wins++;
            }
            else if (g.getGameDecision() == 2)
            {
                loss++;
            }
        }

        return wins + "-" + loss;
    }

    /**
     * Method which gets total pitches
     *
     * @return total pitches
     */
    public int getTotalPitches()
    {
        int totalPitches = 0;

        for (Game g : gamesList)
        {
            totalPitches += g.getPitchCount();
        }

        return totalPitches;
    }

    /**
     * Method which calculates the strikeout rate
     *
     * @return the strikeout rate
     */
    public double getStrikeoutRate()
    {
        double totalStrikeouts = getTotalStrikeouts();

        double totalBattersFaced = 0;

        for (Game g : gamesList)
        {
            totalBattersFaced += g.getBattersFaced();
        }

        return totalStrikeouts/totalBattersFaced;
    }

    /**
     * Method which gets strikeouts per nine innings
     *
     * @return strikeouts per nine innings
     */
    public double getStrikeoutPerNine()
    {
        double totalStrikeouts = getTotalStrikeouts();
        double totalInningsPitched = 0;

        for (Game g : gamesList)
        {
            totalInningsPitched += g.getInningsPitched();
        }

        return 9 * (totalStrikeouts/totalInningsPitched);

    }

    /**
     * To string for Player
     *
     * @return all variables of player object
     */
    @Override
    public String toString()
    {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", height='" + height + '\'' +
                ", weight=" + weight +
                ", uri=" + uri +
                ", gamesList=" + gamesList +
                '}';
    }

    /**
     * Equals method for Player
     *
     * @param o other object
     * @return boolean if equal or not
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id &&
                age == player.age &&
                Double.compare(player.weight, weight) == 0 &&
                firstName.equals(player.firstName) &&
                lastName.equals(player.lastName) &&
                height.equals(player.height) &&
                uri.equals(player.uri);
    }

    /**
     * Hash Code for Player
     *
     * @return player's hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, age, height, weight, uri);
    }
}
