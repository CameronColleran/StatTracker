/**
 * GameLogActivity.java: Class which allows user to enter new games to database and also displays
 * all games already in database
 *
 * @author Cameron Colleran
 * @version 1.0
 */
package edu.miracostacollege.stattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.stattracker.model.DBHelper;
import edu.miracostacollege.stattracker.model.Game;

public class GameLogActivity extends AppCompatActivity
{
    /** Instance variables */
    private DBHelper db;
    private List<Game> gamesList;
    private GameListAdapter gameListAdapter;
    private ListView gameListView;
    Spinner gameDecisionSpinner;


    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_log);

        db = new DBHelper(this);

        gamesList = db.getAllGames();

        gameListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);

        gameListView = findViewById(R.id.gameListView);
        gameListView.setAdapter(gameListAdapter);

        gameDecisionSpinner = findViewById(R.id.gameDecisionSpinner);

        // Creating array for spinner items
        String[] spinnerArray = new String[]{"[Game Decision]", "Win", "Loss", "No Decision"};

        // Creating and setting adapter for spinner
        ArrayAdapter<String> decisionSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        decisionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameDecisionSpinner.setAdapter(decisionSpinnerAdapter);
    }

    /**
     * Method which sends an intent with data from clicked game to GameDetailActivity
     *
     * @param v
     */
    public void viewGameDetails(View v)
    {
        Game selectedGame = (Game) v.getTag();

        Intent intent = new Intent(this, GameDetailsActivity.class);

        intent.putExtra("Opponent", selectedGame.getOpponent());
        intent.putExtra("Date", selectedGame.getDate());
        intent.putExtra("Pitch Count", selectedGame.getPitchCount());
        intent.putExtra("Strikeouts", selectedGame.getStrikeouts());
        intent.putExtra("Innings Pitched", selectedGame.getInningsPitched());
        intent.putExtra("Earned Runs", selectedGame.getEarnedRuns());
        intent.putExtra("Home Runs", selectedGame.getHomeRuns());
        intent.putExtra("Walks", selectedGame.getWalks());
        intent.putExtra("Hits", selectedGame.getHits());
        intent.putExtra("Ground Balls", selectedGame.getGroundBalls());
        intent.putExtra("Batters Faced", selectedGame.getBattersFaced());
        intent.putExtra("Game Decision", selectedGame.getGameDecision());

        startActivity(intent);

    }

    /**
     * Method which reads in data from views and adds game to database
     *
     * @param v
     */
    public void addGame(View v)
    {
        // Wiring up views
        EditText gameDateEditText = findViewById(R.id.gameDateEditText);
        EditText opponentEditText = findViewById(R.id.opponentEditText);
        EditText inningsPitchedEditText = findViewById(R.id.inningsPitchedEditText);
        EditText earnedRunsEditText = findViewById(R.id.earnedRunsEditText);
        EditText homeRunsEditText = findViewById(R.id.homeRunsEditText);
        EditText walksEditText = findViewById(R.id.walksEditText);
        EditText strikeoutsEditText = findViewById(R.id.strikeoutsEditText);
        EditText hitsEditText = findViewById(R.id.hitsEditText);
        EditText groundBallsEditText = findViewById(R.id.groundBallsEditText);
        EditText pitchCountEditText = findViewById(R.id.pitchCountEditText);
        EditText battersFacedEditText = findViewById(R.id.battersFacedEditText);


        // Getting data user entered from views
        String date = gameDateEditText.getText().toString();
        String opponent = opponentEditText.getText().toString();
        int innings = Integer.parseInt(inningsPitchedEditText.getText().toString());
        int earnedRuns = Integer.parseInt(earnedRunsEditText.getText().toString());
        int homeRuns = Integer.parseInt(homeRunsEditText.getText().toString());
        int walks = Integer.parseInt(walksEditText.getText().toString());
        int strikeouts = Integer.parseInt(strikeoutsEditText.getText().toString());
        int hits = Integer.parseInt(hitsEditText.getText().toString());
        int groundBalls = Integer.parseInt(groundBallsEditText.getText().toString());
        int pitchCount = Integer.parseInt(pitchCountEditText.getText().toString());
        int battersFaced = Integer.parseInt(battersFacedEditText.getText().toString());

        // Getting correct decision value depending on input
        String strDecision = gameDecisionSpinner.getSelectedItem().toString();
        int gameDecision;
        if(strDecision.equals("Win"))
            gameDecision = 1;
        else if (strDecision.equals("Loss"))
            gameDecision = 2;
        else
            gameDecision = 0;

        // public Game(long id, String date, String opponent, int inningsPitched, int earnedRuns, int homeRuns, int walks, int strikeouts, int hits, int groundBalls, int pitchCount, int battersFaced, int gameDecision)
        Game game = new Game(date, opponent, innings, earnedRuns, homeRuns, walks, strikeouts, hits, groundBalls, pitchCount, battersFaced, gameDecision);

        // Adding game to database and adapter
        db.addGame(game);
        gameListAdapter.add(game);

        // Resetting views
        gameDateEditText.setText("");
        opponentEditText.setText("");
        inningsPitchedEditText.setText("");
        earnedRunsEditText.setText("");
        homeRunsEditText.setText("");
        walksEditText.setText("");
        strikeoutsEditText.setText("");
        hitsEditText.setText("");
        groundBallsEditText.setText("");
        pitchCountEditText.setText("");
        battersFacedEditText.setText("");
        gameDecisionSpinner.setSelection(0);

    }
}
