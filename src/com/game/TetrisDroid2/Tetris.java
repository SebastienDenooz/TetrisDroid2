package com.game.TetrisDroid2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

public class Tetris extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Handler handler = new Handler();
    LinearLayout gameBoard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.gameBoard = (LinearLayout) this.findViewById(R.id.gameBoard);
        Game theGame = new Game();
        theGame.main(gameBoard, this);
        champi();
        handler.postDelayed(heartBeat, 1000);
    }

    public void onStop(){
        super.onStop();
        handler.removeCallbacks(heartBeat);
    }

    public void champi(){
        for (int k=0; k<15; k++){
            java.util.Random rand = new java.util.Random();
            int leNombreAleatoire = rand.nextInt(gameBoard.getChildCount());
            LinearLayout coordX = (LinearLayout) gameBoard.getChildAt(leNombreAleatoire);
            int leNombreAleatoirey = rand.nextInt(coordX.getChildCount());
            LinearLayout coordXY = (LinearLayout) coordX.getChildAt(leNombreAleatoirey);

            if (coordXY.getChildCount() < 1){
                System.out.println("Create square");
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
                Square btn = new Square(this);
                btn.setLayoutParams(params);
                btn.setBackgroundColor(Color.WHITE);
                coordXY.addView(btn);
            }
        }
    }


    private void removeFullLines(){

    }

    private void fillEmptyLastLines(){
        for (int x = 0; x < gameBoard.getChildCount();x++){
            LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
            for (int y = column.getChildCount()-2;y>=0;y--){
                LinearLayout gridCase = (LinearLayout) column.getChildAt(y);
                LinearLayout nextGridCase = (LinearLayout) column.getChildAt(y+1);
                Square square = (Square) gridCase.getChildAt(0);
                if (nextGridCase.getChildCount() == 0 & gridCase.getChildCount() == 1){
                    gridCase.removeViewAt(0);
                    nextGridCase.addView(square);
                    if (y > 18) y--;

                }
            }
        }
    }

    private void throwNewElement(){

    }

    private boolean downElement(){
        if (false){
            throwNewElement();
        }
        return true;
    }

    private void turnElementLeft(){

    }

    private void turnElementRight(){

    }

    private void redrawGameBoard(){
        for (int x = 0; x < gameBoard.getChildCount();x++){
            LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
            for (int y = column.getChildCount()-2;y>=0;y--){
                LinearLayout gridCase = (LinearLayout) column.getChildAt(y);
                    gridCase.removeViewAt(0);
            }
        }
    }

    private Runnable heartBeat = new Runnable() {

        @Override
        public void run() {
        removeFullLines();
        fillEmptyLastLines();
        downElement();
        //redrawGameBoard();
        handler.postDelayed(this, 1000);
        }
    };

}
