/**
 * MainActivity.java: Main Activity for the app, where everything is accessed from
 *
 * @author Cameron Colleran
 * @version 1.0
 */


package edu.miracostacollege.stattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.stattracker.model.DBHelper;
import edu.miracostacollege.stattracker.model.Game;
import edu.miracostacollege.stattracker.model.Player;

public class MainActivity extends AppCompatActivity
{
    /** Instance variables */
    DBHelper db;
    ImageView playerImageView;
    TextView nameTextView;
    TextView ageTextView;
    TextView heightTextView;
    TextView weightTextView;

    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        //db.deleteAllGames();
        //db.deleteAllPlayers();









        /*
        Game g1 = new Game("April 12, 2019", "Arizona Diamondbacks", 5, 1, 0, 1, 5, 3, 6, 88, 20, 1);
        Game g2 = new Game("May 14, 2019", "Los Angeles Dodgers", 4, 3, 2, 1, 3, 5, 7, 92, 22, 2);
        Game g3 = new Game("August 10, 2019", "Colorado Rockies", 6, 3, 1, 1, 5, 5, 10, 86, 25, 1);
        Game g4 = new Game("August 29, 2019", "San Francisco Giants", 7, 1, 1, 0, 8, 5, 3, 92, 26, 1);

        db.addGame(g1);
        db.addGame(g2);
        db.addGame(g3);
        db.addGame(g4);


         */











        // Getting players from database
        List<Player> players = db.getAllPlayers();


        // Wiring up views
        playerImageView = findViewById(R.id.playerImageView);
        nameTextView = findViewById(R.id.nameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        heightTextView = findViewById(R.id.heightTextView);
        weightTextView = findViewById(R.id.weightTextView);
        TextView eraTextView = findViewById(R.id.eraTextView);
        TextView strikeoutsTextView = findViewById(R.id.strikeoutsTextView);
        TextView whipTextView = findViewById(R.id.whipTextView);

        // Getting player from list and setting its game list to game list from database
        Player player = players.get(0);
        List<Game> gameList = db.getAllGames();
        System.out.println("GAMES LIST SIZE: " + gameList.size());
        player.setGamesList(gameList);

        // Setting all views from corresponding data
        playerImageView.setImageURI(player.getUri());
        nameTextView.setText(player.getFirstName() + " " + player.getLastName());
        ageTextView.setText(String.valueOf(player.getAge()));
        heightTextView.setText(player.getHeight());
        weightTextView.setText(String.valueOf(player.getWeight()));
        NumberFormat format = new DecimalFormat("#0.00");
        eraTextView.setText(format.format(player.calculateEra()));
        strikeoutsTextView.setText(String.valueOf(player.getTotalStrikeouts()));
        whipTextView.setText(format.format(player.calculateWhip()));
    }

    /**
     * OnResume
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        List<Player> players = db.getAllPlayers();

        Player player = players.get(0);
        playerImageView.setImageURI(player.getUri());
        nameTextView.setText(player.getFirstName() + " " + player.getLastName());
        ageTextView.setText(String.valueOf(player.getAge()));
        heightTextView.setText(player.getHeight());
        weightTextView.setText(String.valueOf(player.getWeight()));
    }

    /**
     * Method which starts an intent and goes to GameLogActivity
     *
     * @param v
     */
    public void goToGameLog(View v)
    {
        Intent intent = new Intent(this, GameLogActivity.class);
        startActivity(intent);
    }

    /**
     * Method which starts an intent and goes to StatBreakdownActivity
     *
     * @param v
     */
    public void goToStatActivity(View v)
    {
        Intent intent = new Intent(this, StatBreakdownActivity.class);
        startActivity(intent);
    }

    /**
     * Method which starts an intent and goes to EditDetailsActivity
     *
     * @param v
     */
    public void goToEditDetailsActivity(View v)
    {
        Intent intent = new Intent(this, EditDetailsActivity.class);
        startActivity(intent);
    }

    /**
     * Method which starts an intent and goes to MapsActivity
     *
     * @param v
     */
    public void goToMapsActivity(View v)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
