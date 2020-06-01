/**
 * DBHelperTestTest.java: CLass to safely test the DBHelper class
 */

package edu.miracostacollege.stattracker.model;

import android.content.Context;

import android.net.Uri;

import androidx.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBHelperTestTest
{
    /** Instance variables */
    private static DBHelperTest testDB;
    private static Context testContext;
    private Player testPlayer;
    private Game testGame;

    /**
     * SetUp method
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        testContext = InstrumentationRegistry.getTargetContext();
        testDB = new DBHelperTest(testContext);
        testContext.deleteDatabase(DBHelperTest.DATABASE_NAME);

        // Arbitrary Uri for testing
        Uri uri = Uri.parse("android.resource://edu.miracostacollege.stattracker/drawable/anon_player");

        testPlayer = new Player("Cameron", "Colleran", 20, "5'10", 165, uri);
        testGame = new Game("April 12, 2019", "Arizona Diamondbacks", 5, 1, 0, 1, 5, 3, 6, 88, 20, 1);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void onCreate()
    {
    }

    @Test
    public void onUpgrade()
    {
    }

    /**
     * Method to test the add player method
     */
    @Test
    public void addPlayer()
    {
        for (int i = 1; i <= 100; i++)
        {
            testDB.addPlayer(testPlayer);
            assertEquals("Testing size of database", i, testDB.getAllPlayers().size());
            assertEquals("Testing id of the test player after add", i, testPlayer.getId());
            assertEquals("Test all fields of the player added to database", testPlayer, testDB.getAllPlayers().get(i-1));
        }
    }

    /**
     * Method to test the add game method
     */
    @Test
    public void addGame()
    {
        for (int i = 1; i <= 100; i++)
        {
            testDB.addGame(testGame);
            assertEquals("Testing size of database", i, testDB.getAllGames().size());
            assertEquals("Testing id of the test game after add", i, testGame.getId());
            assertEquals("Test all fields of the game added to database", testGame, testDB.getAllGames().get(i-1));
        }
    }

    /**
     * Method to test the getAllGames method
     */
    @Test
    public void getAllGames()
    {
        assertEquals("Testing size of all games", 0, testDB.getAllGames().size());
        testDB.addGame(testGame);
        assertEquals("Testing size of all games", 1, testDB.getAllGames().size());
    }

    /**
     * Method to test the getAllPlayers method
     */
    @Test
    public void getAllPlayers()
    {
        assertEquals("Testing size of all players", 0, testDB.getAllPlayers().size());
        testDB.addPlayer(testPlayer);
        assertEquals("Testing size of all players", 1, testDB.getAllPlayers().size());
    }

    /**
     * Method to test the deleteAllGames method
     */
    @Test
    public void deleteAllGames()
    {
        testDB.addGame(testGame);
        testDB.addGame(testGame);
        assertEquals("Testing size of all games", 2, testDB.getAllGames().size());
        testDB.deleteAllGames();
        assertEquals("Testing size of all games", 0, testDB.getAllGames().size());
    }

    /**
     * Method to test the updatePlayer method
     */
    @Test
    public void updatePlayer()
    {
        // Adding player
        testDB.addPlayer(testPlayer);
        assertEquals("Test all fields of the player added to database", testPlayer, testDB.getAllPlayers().get(0));

        // Changing fields and re adding player
        testPlayer.setFirstName("New First Name");
        testPlayer.setLastName("New Last Name");
        testDB.updatePlayer(testPlayer);
        assertEquals("Test all fields of the player added to database", testPlayer, testDB.getAllPlayers().get(0));
    }
}