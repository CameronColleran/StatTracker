/**
 * DBHelper.java: Database class to store data persistently
 *
 * @author Cameron Colleran
 * @version 1.0
 */

package edu.miracostacollege.stattracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper
{

    private Context context;

    // Defining the database version and name
    public static final String DATABASE_NAME = "StatTracker";
    private static final int DATABASE_VERSION = 1;

    // Defining the fields for the Player Table (will only store one player, acting as a 'profile')
    private static final String PLAYER_TABLE = "Player";
    private static final String PLAYER_KEY_FIELD_ID = "_id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_AGE = "age";
    private static final String FIELD_HEIGHT = "height";
    private static final String FIELD_WEIGHT = "weight";
    private static final String FIELD_IMAGE_URI = "uri";

    // Defining fields for the Game Table
    private static final String GAMES_TABLE = "Games";
    private static final String GAMES_KEY_FIELD_ID = "_id";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_OPPONENT = "opponent";
    private static final String FIELD_INNINGS_PITCHED = "innings_pitched";
    private static final String FIELD_EARNED_RUNS = "earned_runs";
    private static final String FIELD_HOME_RUNS = "home_runs";
    private static final String FIELD_WALKS = "walks";
    private static final String FIELD_STRIKEOUTS = "strikeouts";
    private static final String FIELD_HITS = "hits";
    private static final String FIELD_GROUND_BALLS = "ground_balls";
    private static final String FIELD_PITCH_COUNT = "pitch_count";
    private static final String FIELD_BATTERS_FACED = "batters_faced";
    private static final String FIELD_GAME_DECISION = "game_decision";


    /**
     * Constructor
     *
     * @param context
     */
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * OnCreate
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createQuery = "CREATE TABLE IF NOT EXISTS " + PLAYER_TABLE + "("
                + PLAYER_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_FIRST_NAME + " TEXT, "
                + FIELD_LAST_NAME + " TEXT, "
                + FIELD_AGE + " INTEGER, "
                + FIELD_HEIGHT + " TEXT, "
                + FIELD_WEIGHT + " REAL, "
                + FIELD_IMAGE_URI + " TEXT" + ")";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + GAMES_TABLE + "("
                + GAMES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DATE + " TEXT, "
                + FIELD_OPPONENT + " TEXT, "
                + FIELD_INNINGS_PITCHED + " INTEGER, "
                + FIELD_EARNED_RUNS + " INTEGER, "
                + FIELD_HOME_RUNS + " INTEGER, "
                + FIELD_WALKS + " INTEGER, "
                + FIELD_STRIKEOUTS + " INTEGER, "
                + FIELD_HITS + " INTEGER, "
                + FIELD_GROUND_BALLS + " INTEGER, "
                + FIELD_PITCH_COUNT + " INTEGER, "
                + FIELD_BATTERS_FACED + " INTEGER, "
                + FIELD_GAME_DECISION + " INTEGER" + ")";
        db.execSQL(createQuery);

    }

    /**
     * OnUpgrade
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GAMES_TABLE);
        onCreate(db);
    }

    /**
     * Method which adds a player to the database
     *
     * @param player
     */
    public void addPlayer(Player player)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FIRST_NAME, player.getFirstName());
        values.put(FIELD_LAST_NAME, player.getLastName());
        values.put(FIELD_AGE, player.getAge());
        values.put(FIELD_HEIGHT, player.getHeight());
        values.put(FIELD_WEIGHT, player.getWeight());
        values.put(FIELD_IMAGE_URI, player.getUri().toString());

        long id = db.insert(PLAYER_TABLE, null, values);
        player.setId(id);

        // Close the connection
        db.close();
    }

    /**
     * Method which adds a game to the database
     *
     * @param game
     */
    public void addGame(Game game)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_DATE, game.getDate());
        values.put(FIELD_OPPONENT, game.getOpponent());
        values.put(FIELD_INNINGS_PITCHED, game.getInningsPitched());
        values.put(FIELD_EARNED_RUNS, game.getEarnedRuns());
        values.put(FIELD_HOME_RUNS, game.getHomeRuns());
        values.put(FIELD_WALKS, game.getWalks());
        values.put(FIELD_STRIKEOUTS, game.getStrikeouts());
        values.put(FIELD_HITS, game.getHits());
        values.put(FIELD_GROUND_BALLS, game.getGroundBalls());
        values.put(FIELD_PITCH_COUNT, game.getPitchCount());
        values.put(FIELD_BATTERS_FACED, game.getBattersFaced());
        values.put(FIELD_GAME_DECISION, game.getGameDecision());

        long id = db.insert(GAMES_TABLE, null, values);
        game.setId(id);


        // Close the connection
        db.close();
    }

    /**
     * Method which returns all games in the database as a list
     *
     * @return games list
     */
    public List<Game> getAllGames()
    {
        List<Game> gamesList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                GAMES_TABLE,
                new String[]{GAMES_KEY_FIELD_ID, FIELD_DATE, FIELD_OPPONENT, FIELD_INNINGS_PITCHED,
                        FIELD_EARNED_RUNS, FIELD_HOME_RUNS, FIELD_WALKS, FIELD_STRIKEOUTS,
                FIELD_HITS, FIELD_GROUND_BALLS, FIELD_PITCH_COUNT, FIELD_BATTERS_FACED, FIELD_GAME_DECISION},
                null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Game game =
                        new Game(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getInt(4),
                                cursor.getInt(5),
                                cursor.getInt(6),
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getInt(10),
                                cursor.getInt(11),
                                cursor.getInt(12));
                gamesList.add(game);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return gamesList;
    }

    /**
     * Method which returns all players as a list
     *
     * @return players list
     */
    public List<Player> getAllPlayers()
    {
        List<Player> playerList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                PLAYER_TABLE,
                new String[]{PLAYER_KEY_FIELD_ID, FIELD_FIRST_NAME, FIELD_LAST_NAME, FIELD_AGE,
                        FIELD_HEIGHT, FIELD_WEIGHT, FIELD_IMAGE_URI},
                null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Player player = new Player(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getDouble(5),
                        Uri.parse(cursor.getString(6)));

                playerList.add(player);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return playerList;
    }

    /**
     * Method which deletes all games from the database
     */
    public void deleteAllGames()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(GAMES_TABLE, null, null);
        db.close();
    }

    /**
     * Method which deletes all players from the database
     */
    public void deleteAllPlayers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PLAYER_TABLE, null, null);
        db.close();
    }

    /**
     * Method which updates player fields in the database
     *
     * @param player
     */
    public void updatePlayer(Player player)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FIRST_NAME, player.getFirstName());
        values.put(FIELD_LAST_NAME, player.getLastName());
        values.put(FIELD_AGE, player.getAge());
        values.put(FIELD_HEIGHT, player.getHeight());
        values.put(FIELD_WEIGHT, player.getWeight());
        values.put(FIELD_IMAGE_URI, player.getUri().toString());

        database.update(PLAYER_TABLE, values, PLAYER_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(player.getId())});

        database.close();
    }

}
