/**
 * GameDetailsActivity.java: Class which shows details of game clicked in GameLog
 *
 * @author Cameron Colleran
 * @version 1.0
 */

package edu.miracostacollege.stattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameDetailsActivity extends AppCompatActivity
{

    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // Wiring up views
        TextView gameOpponentDetailsTextView = findViewById(R.id.gameOpponentDetailsTextView);
        TextView dateDetailsTextView = findViewById(R.id.dateDetailsTextView);
        TextView pitchCountDetailsTextView = findViewById(R.id.pitchCountDetailsTextView);
        TextView strikeoutsDetailsTextView = findViewById(R.id.strikeoutsDetailsTextView);
        TextView earnedRunsDetailsTextView = findViewById(R.id.earnedRunsDetailsTextView);
        TextView gameDecisionTextView = findViewById(R.id.gameDecisionTextView);
        TextView inningsPitchedDetailsTextView = findViewById(R.id.inningsPitchedDetailsTextView);
        TextView hitsDetailsTextView = findViewById(R.id.hitsDetailsTextView);
        TextView walksDetailsTextView = findViewById(R.id.walksDetailsTextView);
        TextView homeRunsDetailsTextView = findViewById(R.id.homeRunsDetailsTextView);
        TextView groundBallsDetailsTextView = findViewById(R.id.groundBallsDetailsTextView);
        TextView battersFacedDetailsTextView = findViewById(R.id.battersFacedDetailsTextView);

        // Getting intent
        Intent intent = getIntent();


        // Getting intent data
        String opponent = intent.getStringExtra("Opponent");
        String date = intent.getStringExtra("Date");
        int pitchCount = intent.getIntExtra("Pitch Count", 0);
        int strikeouts = intent.getIntExtra("Strikeouts", 0);
        int inningsPitched = intent.getIntExtra("Innings Pitched",0);
        int earnedRuns = intent.getIntExtra("Earned Runs",0);
        int homeRuns = intent.getIntExtra("Home Runs", 0);
        int walks = intent.getIntExtra("Walks",0);
        int hits = intent.getIntExtra("Hits",0);
        int groundBalls = intent.getIntExtra("Ground Balls",0);
        int battersFaced = intent.getIntExtra("Batters Faced", 0);
        int gameDecision = intent.getIntExtra("Game Decision",0);

        // Setting text views with data
        gameOpponentDetailsTextView.setText(opponent);
        dateDetailsTextView.setText(date);
        pitchCountDetailsTextView.setText(String.valueOf(pitchCount));
        strikeoutsDetailsTextView.setText(String.valueOf(strikeouts));
        earnedRunsDetailsTextView.setText(String.valueOf(earnedRuns));
        inningsPitchedDetailsTextView.setText(String.valueOf(inningsPitched));
        hitsDetailsTextView.setText(String.valueOf(hits));
        walksDetailsTextView.setText(String.valueOf(walks));
        homeRunsDetailsTextView.setText(String.valueOf(homeRuns));
        groundBallsDetailsTextView.setText(String.valueOf(groundBalls));
        battersFacedDetailsTextView.setText(String.valueOf(battersFaced));

        // Setting game decision text view
        if (gameDecision == 0)
        {
            String decision = "ND";
            gameDecisionTextView.setText(decision);
        }
        else if (gameDecision == 1)
        {
            String decision = "W";
            gameDecisionTextView.setText(decision);
        }
        else if (gameDecision == 2)
        {
            String decision = "L";
            gameDecisionTextView.setText(decision);
        }

    }
}
