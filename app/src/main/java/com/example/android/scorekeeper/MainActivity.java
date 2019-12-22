package com.example.android.scorekeeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Member variables for holding the score of each team
    private int mScore1;    //int variables declared at file scope are initialized to 0
    private int mScore2;
    private static final String STATE_SCORE_1 = "Team 1 Score";
    private static final String STATE_SCORE_2 = "Team 2 Score";

    //Member variables for holding references to textviews that display the scores
    private TextView mScoreText1;
    private TextView mScoreText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find and initialize the textviews
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt((STATE_SCORE_2));
            setScoreTeam1(mScore1);
            setScoreTeam2(mScore2);
        }
    }

    public void decreaseScore(View view) {

        //Get the id of the button that was clicked
        int viewID = view.getId();

        switch (viewID) {
            //if team1, decrease the score of team1 and update the textview
            case R.id.decreaseTeam1:
                mScore1--;
                setScoreTeam1(mScore1);
                break;
            //if team2, decrease the score of team2, and update that textview
            case R.id.decreaseTeam2:
                mScore2--;
                setScoreTeam2(mScore2);
                break;
        }
    }

    public void increaseScore(View view) {
        //Get the id of the button that was clicked
        int viewID = view.getId();

        switch (viewID) {
            //if team1, increase the score of team1 and update the textview
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //if team2, increase the score of team2, and update that textview
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    /**
     * MENU SETTINGS
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.night_mode) {
            //Get the night mode setting
            int mode = AppCompatDelegate.getDefaultNightMode();
            if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            recreate();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Persist scores on device rotation
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }

    /**
     * HELPER METHODS
     */

    private void setScoreTeam1 (int scoreTeam1) {
        mScoreText1.setText(String.valueOf(scoreTeam1));
    }

    private void setScoreTeam2 (int scoreTeam2) {
        mScoreText2.setText(String.valueOf(scoreTeam2));
    }
}
