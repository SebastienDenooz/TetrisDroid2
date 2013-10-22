package com.game.TetrisDroid2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
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
        handler.postDelayed(heartBeat, 1);
    }

    public void champi(){
        java.util.Random rand = new java.util.Random();
        int leNombreAleatoire = rand.nextInt(gameBoard.getChildCount());
        LinearLayout coordX = (LinearLayout) gameBoard.getChildAt(leNombreAleatoire);
        int leNombreAleatoirey = rand.nextInt(coordX.getChildCount());
        LinearLayout coordXY = (LinearLayout) coordX.getChildAt(leNombreAleatoirey);

        if (coordXY.getChildCount() < 1){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
            ImageView btn = new ImageView(this);
            btn.setLayoutParams(params);
            btn.setBackgroundColor(Color.WHITE);
            coordXY.addView(btn);
        }
    }

    private Runnable heartBeat = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
        }
    };

}
