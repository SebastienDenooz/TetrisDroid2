package com.game.TetrisDroid2;

import android.content.Context;
import android.os.Handler;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static LinearLayout gameBoard;
    private static Context context;
    private Random generator = new Random();

    public int [] gameBoardDimension;

    static Element fallingElement;
    List<Square> bottomSquareList = new ArrayList<Square>();


    public Game( LinearLayout _gameBoard, int[] _gameBoardDimension, Context _context) {
        gameBoard = _gameBoard;
        context = _context;
        gameBoardDimension = _gameBoardDimension;

        for (int i = 0; i < gameBoardDimension[0]; i++){

            LinearLayout verticalLine = new LinearLayout(context);
            verticalLine.setOrientation(LinearLayout.VERTICAL);
            verticalLine.setWeightSum(gameBoardDimension[1]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
            verticalLine.setLayoutParams(params);
            verticalLine.setId(i);

            gameBoard.addView(verticalLine);

            for (int j = 0; j < gameBoardDimension[1]; j++){

                LinearLayout gridCase = new LinearLayout(context);
                LinearLayout.LayoutParams caseParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
                gridCase.setLayoutParams(caseParams);
                gridCase.setWeightSum(1);
                gridCase.setOrientation(LinearLayout.VERTICAL);
                gridCase.setId(j);
                verticalLine.addView(gridCase);
            }

        }
        fallingElement = new Element(generator.nextInt(5),generator.nextInt(4),_gameBoard, context);
    }


    public void throwNewElement(){
        if (fallingElement != null){
            for (Square square : fallingElement._squaresList ){
                bottomSquareList.add(square);
            }
        }
        fallingElement = new Element(generator.nextInt(5),generator.nextInt(4),gameBoard, context);
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

}