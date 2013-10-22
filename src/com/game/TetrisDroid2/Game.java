package com.game.TetrisDroid2;

import android.content.Context;
import android.widget.LinearLayout;

public class Game {

    private static LinearLayout gameBoard;
    private static Context context;
    private static int [] gameBoardDimension = {15,20};


    public static void main( LinearLayout _gameBoard, Context _context) {
        gameBoard = _gameBoard;
        context = _context;

        for (int i = 0; i < gameBoardDimension[0]; i++){

            LinearLayout verticalLine = new LinearLayout(context);
            verticalLine.setOrientation(LinearLayout.VERTICAL);
            verticalLine.setWeightSum(gameBoardDimension[1]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
            verticalLine.setLayoutParams(params);

            gameBoard.addView(verticalLine);

            for (int j = 0; j < gameBoardDimension[1]; j++){

                LinearLayout gridCase = new LinearLayout(context);
                LinearLayout.LayoutParams caseParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
                gridCase.setLayoutParams(caseParams);
                gridCase.setWeightSum(1);
                gridCase.setOrientation(LinearLayout.VERTICAL);
                verticalLine.addView(gridCase);
            }

        }
    }

}