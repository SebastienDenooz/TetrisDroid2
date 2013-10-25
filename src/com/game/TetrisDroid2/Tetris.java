package com.game.TetrisDroid2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.*;

public class Tetris extends Activity {
    /**
     * Called when the activity is first created.
     */

    LinearLayout gameBoard;
    public static Game theGame;
    public static int [] gameBoardDimension = {15,20};
    public Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addListenerOnButton();

        this.gameBoard = (LinearLayout) this.findViewById(R.id.gameBoard);
        theGame = new Game(gameBoard, gameBoardDimension, this);
        heartBeat.run();
    }

    public void onStop(){
        super.onStop();
        handler.removeCallbacks(heartBeat);
    }


    private void removeFullLines(){
        List<Integer> newEmptyLine = new ArrayList<Integer>();
        for (int y = theGame.gameBoardDimension[1]-1; y >= 0; y--){
            boolean lineIsFull = true;
            for (int x = 0; x <= theGame.gameBoardDimension[0]-1; x++){
                LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
                LinearLayout gridCase = (LinearLayout) column.getChildAt(y);
                if(gridCase.getChildCount() < 1){ lineIsFull = false; }
            }
            if (lineIsFull){
                newEmptyLine.add(y);
                for (int x = 0; x <= theGame.gameBoardDimension[0]-1; x++){
                    LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
                    LinearLayout gridCase = (LinearLayout) column.getChildAt(y);
                    Square squareToRemove = (Square) gridCase.getChildAt(0);
                    gridCase.removeView(squareToRemove);
                    theGame.bottomSquareList.remove(squareToRemove);
                    squareToRemove = null;
                }
            }
        }
        if (newEmptyLine.size()>0){
            fillEmptyLines(newEmptyLine);
        }
    }

    private void fillEmptyLines(List<Integer> lines){
        int i = 0;
        for (int line : lines ){
            int startAt = line-i;
            for (int y = startAt ; y >= 1; y--){
                for (int x = 0; x <= theGame.gameBoardDimension[0]-1; x++){
                    LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
                    LinearLayout gridCase = (LinearLayout) column.getChildAt(y-1);
                    LinearLayout newGridCase = (LinearLayout) column.getChildAt(y);
                    Square squareToMove = (Square) gridCase.getChildAt(0);
                    if (squareToMove != null){
                        gridCase.removeView(squareToMove);
                        newGridCase.addView(squareToMove);
                    }
                }

            }
            i++;
        }
    }

    public void addListenerOnButton() {
        ImageButton butn = (ImageButton) findViewById(R.id.btnTurnRight);
        ImageButton butnLeft = (ImageButton) findViewById(R.id.btnTurnLeft);
        ImageButton straffLeft = (ImageButton) findViewById(R.id.btnGoLeft);
        ImageButton straffRight = (ImageButton) findViewById(R.id.btnGoRight);
        ImageButton goDown = (ImageButton) findViewById(R.id.btnGoDown);

        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.rotateLeft();
            }
        });

        goDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                boolean isDown = theElement.downElement();
                while(isDown){
                    isDown = theElement.downElement();
                }
            }
        });


        butnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.rotateLeft();
            }
        });


        straffLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.strafElementLeft();
            }
        });


        straffRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.strafElementRight();
            }
        });


    }


    public Runnable heartBeat = new Runnable() {

        @Override
        public void run() {

            Element theElement = theGame.fallingElement;
            boolean isDown = theElement.downElement();
            if (!isDown){
                removeFullLines();
                theGame.throwNewElement();
            }
            handler.postDelayed(this, 1000);
        }
    };

}
