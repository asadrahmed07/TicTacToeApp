package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    // 0 = X
    // 1 = o
    int activePlayer = 0;
    int count = 0;

    int[] gameState =  {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // Game State meanings :
    // 0  =  X
    // 1  = O
    // 2 = Null

    int[][] WinPosition = {{0,1,2} , {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                                {0,4,8},{2,4,6}};
    public void playerTap(View view) {
       ImageView img = (ImageView) view;
       int tappedImage = Integer.parseInt(img.getTag().toString());
       if(!gameActive) {
           gameReset(view);
       }
       if (gameState[tappedImage] == 2)  {
          gameState[tappedImage] = activePlayer;
          img.setTranslationY(-1000f);
          if(activePlayer == 0) {
              img.setImageResource(R.drawable.x1);
              activePlayer = 1;
              TextView status = findViewById(R.id.status);
              status.setText(" O's Turn Tap To Play");
              count++;
          }
          else {
              img.setImageResource(R.drawable.o1);
              activePlayer = 0;
              TextView status = findViewById(R.id.status);
              status.setText(" X's Turn Tap To Play");
              count++;
          }

           img.animate().translationYBy(1000f).setDuration(300);
       }

       for(int[] Win : WinPosition) {
           if(gameState[Win[0]] == gameState[Win[1]] && gameState[Win[1]] == gameState[Win[2]] && gameState[Win[0]] != 2) {
               String WinnerStr ;
               gameActive = false;
               if(gameState[Win[0]] == 0) {
                    WinnerStr = " Congratulations!!!  X Won!!";
                }
               else {
                   WinnerStr = " Congratulations!!!  O Won!!";
               }
               TextView status = findViewById(R.id.status);
               status.setText(WinnerStr);
           }
       }
       if(gameActive && count == 9) {
           TextView status = findViewById(R.id.status);
           status.setText("OOPS!!! It's A DRAW!!");
           gameActive = false;

       }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        count = 0;
        TextView status = findViewById(R.id.status);
        status.setText(" X's Turn Tap To Play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}