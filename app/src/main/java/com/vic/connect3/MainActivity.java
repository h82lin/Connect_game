package com.vic.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    boolean gameisActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameisActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().rotation(360).translationYBy(1000f).setDuration(300);

            for(int[] winningPosition:winningPositions){

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2 ){
                    gameisActive = false;
                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0){
                        winner = "Yellow";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnermessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
                    layout.setVisibility(View.VISIBLE);
                }
                else {

                    boolean gameIsOver = true;
                    for (int counterState: gameState){

                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                    if (gameIsOver == true){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnermessage);
                        winnerMessage.setText("It's a draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playagain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        gameisActive = true;
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
