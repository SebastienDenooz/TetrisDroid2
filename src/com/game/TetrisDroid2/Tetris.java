package com.game.TetrisDroid2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Tetris extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout gameBoard = (LinearLayout) this.findViewById(R.id.gameBoard);
        Game theGame = new Game();
        theGame.main(gameBoard, this);

    }
}
