/**
 * Game.java: Class which represents a game and all corresponding stats
 *
 * @author Cameron Colleran
 * @version 1.0
 */

package edu.miracostacollege.stattracker.model;

import java.util.Objects;

public class Game
{
    /** Instance variables */
    private long id;
    private String date;
    private String opponent;
    private int inningsPitched;
    private int earnedRuns;
    private int homeRuns;
    private int walks;
    private int strikeouts;
    private int hits;
    private int groundBalls;
    private int pitchCount;
    private int battersFaced;
    private int gameDecision; // 0 = nd, 1 = win, 2 = loss

    /**
     * Empty constructor
     */
    public Game()
    {

    }

    /**
     * Overloaded constructor
     *
     * @param id
     * @param date
     * @param opponent
     * @param inningsPitched
     * @param earnedRuns
     * @param homeRuns
     * @param walks
     * @param strikeouts
     * @param hits
     * @param groundBalls
     * @param pitchCount
     * @param battersFaced
     * @param gameDecision
     */
    public Game(long id, String date, String opponent, int inningsPitched, int earnedRuns, int homeRuns, int walks, int strikeouts, int hits, int groundBalls, int pitchCount, int battersFaced, int gameDecision)
    {
        this.id = id;
        this.date = date;
        this.opponent = opponent;
        this.inningsPitched = inningsPitched;
        this.earnedRuns = earnedRuns;
        this.homeRuns = homeRuns;
        this.walks = walks;
        this.strikeouts = strikeouts;
        this.hits = hits;
        this.groundBalls = groundBalls;
        this.pitchCount = pitchCount;
        this.battersFaced = battersFaced;
        this.gameDecision = gameDecision;
    }

    /**
     * Overloaded constructor
     *
     * @param date
     * @param opponent
     * @param inningsPitched
     * @param earnedRuns
     * @param homeRuns
     * @param walks
     * @param strikeouts
     * @param hits
     * @param groundBalls
     * @param pitchCount
     * @param battersFaced
     * @param gameDecision
     */
    public Game(String date, String opponent, int inningsPitched, int earnedRuns, int homeRuns, int walks, int strikeouts, int hits, int groundBalls, int pitchCount, int battersFaced, int gameDecision)
    {
        this(-1, date, opponent, inningsPitched, earnedRuns, homeRuns, walks, strikeouts, hits, groundBalls, pitchCount, battersFaced, gameDecision);
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
     * Getter for date
     *
     * @return date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Setter for date
     *
     * @param date
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * Getter for opponent
     *
     * @return opponent
     */
    public String getOpponent()
    {
        return opponent;
    }

    /**
     * Setter for opponent
     *
     * @param opponent
     */
    public void setOpponent(String opponent)
    {
        this.opponent = opponent;
    }

    /**
     * Getter for innings pitched
     *
     * @return innings pitched
     */
    public int getInningsPitched()
    {
        return inningsPitched;
    }

    /**
     * Setter for innings pitched
     *
     * @param inningsPitched
     */
    public void setInningsPitched(int inningsPitched)
    {
        this.inningsPitched = inningsPitched;
    }

    /**
     * Getter for earned runs
     *
     * @return earned runs
     */
    public int getEarnedRuns()
    {
        return earnedRuns;
    }

    /**
     * Setter for earned runs
     *
     * @param earnedRuns
     */
    public void setEarnedRuns(int earnedRuns)
    {
        this.earnedRuns = earnedRuns;
    }

    /**
     * Getter for home runs
     *
     * @return home runs
     */
    public int getHomeRuns()
    {
        return homeRuns;
    }

    /**
     * Setter for home runs
     *
     * @param homeRuns
     */
    public void setHomeRuns(int homeRuns)
    {
        this.homeRuns = homeRuns;
    }

    /**
     * Getter for walks
     *
     * @return walks
     */
    public int getWalks()
    {
        return walks;
    }

    /**
     * Setter for walks
     *
     * @param walks
     */
    public void setWalks(int walks)
    {
        this.walks = walks;
    }

    /**
     * Getter for strikeouts
     *
     * @return strikeouts
     */
    public int getStrikeouts()
    {
        return strikeouts;
    }

    /**
     * Setter for strikeouts
     *
     * @param strikeouts
     */
    public void setStrikeouts(int strikeouts)
    {
        this.strikeouts = strikeouts;
    }

    /**
     * Getter for hits
     *
     * @return hits
     */
    public int getHits()
    {
        return hits;
    }

    /**
     * Setter for hits
     *
     * @param hits
     */
    public void setHits(int hits)
    {
        this.hits = hits;
    }

    /**
     * Getter for ground balls
     *
     * @return ground balls
     */
    public int getGroundBalls()
    {
        return groundBalls;
    }

    /**
     * Setter for ground balls
     *
     * @param groundBalls
     */
    public void setGroundBalls(int groundBalls)
    {
        this.groundBalls = groundBalls;
    }

    /**
     * Getter for pitch count
     *
     * @return pitch count
     */
    public int getPitchCount()
    {
        return pitchCount;
    }

    /**
     * Setter for pitch count
     *
     * @param pitchCount
     */
    public void setPitchCount(int pitchCount)
    {
        this.pitchCount = pitchCount;
    }

    /**
     * Getter for batters faced
     *
     * @return batters faced
     */
    public int getBattersFaced()
    {
        return battersFaced;
    }

    /**
     * Setter for batters faced
     *
     * @param battersFaced
     */
    public void setBattersFaced(int battersFaced)
    {
        this.battersFaced = battersFaced;
    }

    /**
     * Getter for game decision
     *
     * @return game decision
     */
    public int getGameDecision()
    {
        return gameDecision;
    }

    /**
     * Setter for game decision
     *
     * @param gameDecision
     */
    public void setGameDecision(int gameDecision)
    {
        this.gameDecision = gameDecision;
    }

    /**
     * To string for Game
     *
     * @return all variables of game object
     */
    @Override
    public String toString()
    {
        return "Game{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", opponent='" + opponent + '\'' +
                ", inningsPitched=" + inningsPitched +
                ", earnedRuns=" + earnedRuns +
                ", homeRuns=" + homeRuns +
                ", walks=" + walks +
                ", strikeouts=" + strikeouts +
                ", groundBalls=" + hits +
                ", flyBalls=" + groundBalls +
                ", pitchCount=" + pitchCount +
                ", battersFaced=" + battersFaced +
                ", gameDecision=" + gameDecision +
                '}';
    }

    /**
     * Equals for Game
     *
     * @param o other object
     * @return boolean if equal or not
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                inningsPitched == game.inningsPitched &&
                earnedRuns == game.earnedRuns &&
                homeRuns == game.homeRuns &&
                walks == game.walks &&
                strikeouts == game.strikeouts &&
                hits == game.hits &&
                groundBalls == game.groundBalls &&
                pitchCount == game.pitchCount &&
                battersFaced == game.battersFaced &&
                gameDecision == game.gameDecision &&
                date.equals(game.date) &&
                opponent.equals(game.opponent);
    }

    /**
     * Hash code for Game
     *
     * @return game's hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, date, opponent, inningsPitched, earnedRuns, homeRuns, walks, strikeouts, hits, groundBalls, pitchCount, battersFaced, gameDecision);
    }
}
