package com.example.android.scorefoosball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    int scorePlayerA = 0;
    int scorePlayerB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForPlayerA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Updates the text view as well as visual when player A scores
     *
     * @param view tagID
     */
    public void addOneForPlayerA(View view) {
        scorePlayerA = scorePlayerA + 1;
        if (scorePlayerA >= 10) {
            scorePlayerA = 10;
            Toast.makeText(this, "Player A won", Toast.LENGTH_LONG).show();
            reset(findViewById(R.id.main));
        }
        displayForPlayerA(scorePlayerA);
        updateVisualA(scorePlayerA, 0);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForPlayerB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Updates the text view as well as visual when player B scores
     *
     * @param view tagID
     */
    public void addOneForPlayerB(View view) {
        scorePlayerB = scorePlayerB + 1;
        if (scorePlayerB >= 10) {
            scorePlayerB = 10;
            Toast.makeText(this, "Player B won", Toast.LENGTH_LONG).show();
            reset(findViewById(R.id.main));
        }
        displayForPlayerB(scorePlayerB);
        updateVisualB(scorePlayerB, 0);
    }

    /**
     * Resets the scoreboard.
     *
     * @param view tagID
     */
    public void reset(View view) {
        scorePlayerA = 0;
        scorePlayerB = 0;
        displayForPlayerA(scorePlayerA);
        displayForPlayerB(scorePlayerB);
        updateVisualB(10, 1);
        updateVisualA(10, 1);
    }

    /**
     * Updates the Visual on the screen when player B scores.
     *
     * @param limit     No of dots to be updated
     * @param resetFlag "1" - set all visuals to red.
     *                  "0" - dots equal to score of player B are set to green.
     */
    public void updateVisualB(int limit, int resetFlag) {
        ColorStateList list;
        if (resetFlag == 1) {
            list = ColorStateList.valueOf(Color.rgb(255, 0, 0));
        } else {
            list = ColorStateList.valueOf(Color.rgb(0, 255, 0));
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.player_b_layout);
        for (int i = 0; i < limit; i++) {
            if (limit >= 11) {
                break;
            }
            FloatingActionButton actionButton = (FloatingActionButton) layout.getChildAt(i);
            actionButton.setBackgroundTintList(list);

        }


    }

    /**
     * Updates the Visual on the screen when player A scores.
     *
     * @param limit     No of dots to be updated
     * @param resetFlag "1" - set all visuals to red.
     *                  "0" - dots equal to score of player A are set to green.
     */
    public void updateVisualA(int limit, int resetFlag) {
        ColorStateList list;
        if (resetFlag == 1) {
            list = ColorStateList.valueOf(Color.rgb(255, 0, 0));
        } else {
            list = ColorStateList.valueOf(Color.rgb(0, 255, 0));
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.player_a_layout);
        for (int i = 0; i < limit; i++) {
            if (limit >= 11) {
                break;
            }
            FloatingActionButton actionButton = (FloatingActionButton) layout.getChildAt(i);
            actionButton.setBackgroundTintList(list);

        }
    }

    /**
     * Score for penalty.
     * Takes one parameter to identify the player who has scored for penalty.
     * Hence we can reuse the function for both players instead of duplicating the function
     * for player B.
     */
    public void scoreForPenalty(View view) {
        if (view.getId() == R.id.penalty_score_A) {
            scorePlayerA += 2;
            if (scorePlayerA >= 10) {
                scorePlayerA = 10;
                Toast.makeText(this, "Player A won", Toast.LENGTH_LONG).show();
                reset(findViewById(R.id.main));
            } else {
                displayForPlayerA(scorePlayerA);
                updateVisualA(scorePlayerA, 0);
            }
        } else if (view.getId() == R.id.penalty_score_B) {
            scorePlayerB += 2;
            if (scorePlayerB >= 10) {
                scorePlayerB = 10;
                Toast.makeText(this, "Player B won", Toast.LENGTH_LONG).show();
                reset(findViewById(R.id.main));
            } else {
                displayForPlayerB(scorePlayerB);
                updateVisualB(scorePlayerB, 0);
            }
        }
    }
}