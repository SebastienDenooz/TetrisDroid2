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


    /**
     * Be sure to avoid memory flooding when we are not on the screen.
     */
    public void onStop(){
        super.onStop();
        handler.removeCallbacks(heartBeat);
    }


    /**
     * Remove the line full of square
     */
    private void removeFullLines(){

        List<Integer> newEmptyLine = new ArrayList<Integer>();
        System.out.println(theGame.gameBoardDimension);
        for (int y = theGame.gameBoardDimension[1]-1; y >= 0; y--){

            boolean lineIsFull = true;
            for (int x = 0; x <= theGame.gameBoardDimension[0]-1; x++){
                LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
                LinearLayout gridCase = (LinearLayout) column.getChildAt(y);
                if(gridCase.getChildCount() < 1){ lineIsFull = false; }
            }

            if (lineIsFull){

                // Stock the empty line, to "empile" the square after.
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


    /**
     * Go down the bottom square to fill the full line removed before.
     * @param lines List of removed line's id
     */
    private void fillEmptyLines(List<Integer> lines){

        /** We need to know how many line as ben removed
         * and incremente her new "y" axis
         */
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


    /**
     * Button bindings
     */
    public void addListenerOnButton() {
        ImageButton buttonTurnRight = (ImageButton) findViewById(R.id.btnTurnRight);
        ImageButton buttonTurnLeft = (ImageButton) findViewById(R.id.btnTurnLeft);
        ImageButton buttonGoDown = (ImageButton) findViewById(R.id.btnGoDown);
        ImageButton buttonStrafLeft = (ImageButton) findViewById(R.id.btnGoLeft);
        ImageButton buttonStrafRight = (ImageButton) findViewById(R.id.btnGoRight);

        buttonTurnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.rotateRight();
            }
        });

        buttonTurnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.rotateLeft();
            }
        });

        buttonGoDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                boolean isDown = theElement.downElement();
                while(isDown){
                    isDown = theElement.downElement();
                }
            }
        });

        buttonStrafLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                theElement.strafElementLeft();
            }
        });

        buttonStrafRight.setOnClickListener(new View.OnClickListener() {
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
