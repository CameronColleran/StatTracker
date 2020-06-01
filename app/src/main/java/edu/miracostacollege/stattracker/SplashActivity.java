/**
 * SplashActivity.java: Class which serves as a splash screen on app startup
 *
 * @author Cameron Colleran
 * @version 1.0
 */
package edu.miracostacollege.stattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.miracostacollege.stattracker.model.DBHelper;
import edu.miracostacollege.stattracker.model.Player;

public class SplashActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_splash);

        // Wiring up image view and starting animation
        ImageView loadingImageView = findViewById(R.id.loadingImageView);
        Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        loadingImageView.startAnimation(rotateAnim);

        // Getting players from database
        DBHelper dbHelper = new DBHelper(this);
        final List<Player> players = dbHelper.getAllPlayers();

        // Creating delay to go to next activity
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent intent = null;

                // If there is a player in the database go to MainActivity, otherwise go to Account Creation Activity
                if (players.size() == 1)
                {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                else if (players.size() < 1)
                {
                    intent = new Intent(SplashActivity.this, LogInActivity.class);
                }

                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }
}
