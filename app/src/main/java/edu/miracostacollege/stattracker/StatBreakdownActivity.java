/**
 * StatBreakdownActivity.java: Class which shows an in depth breakdown of stats for the player
 *
 * @author Cameron Colleran
 * @version 1.0
 */

package edu.miracostacollege.stattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import edu.miracostacollege.stattracker.model.DBHelper;
import edu.miracostacollege.stattracker.model.Game;
import edu.miracostacollege.stattracker.model.Player;

public class StatBreakdownActivity extends AppCompatActivity
{
    /** Instance variables */
    private TextView eraBreakdownTextView;
    private TextView strikeoutsBreakdownTextView;
    private TextView whipBreakdownTextView;
    private TextView winLossBreakdownTextView;
    private TextView totalPitchesBreakdownTextView;
    private TextView strikeoutPercentBreakdownTextView;
    private TextView strikeoutPerNineBreakdownTextView;
    private DBHelper db;
    private List<Game> gamesList;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_breakdown);

        db = new DBHelper(this);
        gamesList = db.getAllGames();
        System.out.println(gamesList.size());

        // Setting games list from database to player
        player = new Player();
        player.setGamesList(gamesList);

        // Wiring up views
        eraBreakdownTextView = findViewById(R.id.eraBreakdownTextView);
        strikeoutsBreakdownTextView = findViewById(R.id.strikeoutsBreakdownTextView);
        whipBreakdownTextView = findViewById(R.id.whipBreakdownTextView);
        winLossBreakdownTextView = findViewById(R.id.winLossBreakdownTextView);
        totalPitchesBreakdownTextView = findViewById(R.id.totalPitchesBreakdownTextView);
        strikeoutPercentBreakdownTextView = findViewById(R.id.strikeoutPercentBreakdownTextView);
        strikeoutPerNineBreakdownTextView = findViewById(R.id.strikeoutPerNineBreakdownTextView);

        // Setting views based of game data
        NumberFormat format = new DecimalFormat("#0.00");
        eraBreakdownTextView.setText(format.format(player.calculateEra()));
        strikeoutsBreakdownTextView.setText(String.valueOf(player.getTotalStrikeouts()));
        whipBreakdownTextView.setText(format.format(player.calculateWhip()));
        winLossBreakdownTextView.setText(player.getWinLoss());
        totalPitchesBreakdownTextView.setText(String.valueOf(player.getTotalPitches()));
        strikeoutPercentBreakdownTextView.setText(format.format(100 * player.getStrikeoutRate()) + "%");
        strikeoutPerNineBreakdownTextView.setText(format.format(player.getStrikeoutPerNine()));
    }
}
