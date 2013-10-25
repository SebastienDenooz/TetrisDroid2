package com.game.TetrisDroid2;

import android.content.Context;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {

    private static LinearLayout _gameBoard;
    private static Context _context;
    private Random generator = new Random();

    public int [] gameBoardDimension;

    static Element fallingElement;
    List<Square> bottomSquareList = new ArrayList<Square>(); // List of the square she don't move anymore (not a part of the
                                                        // falling element)

    /**
     * Initialize the game data and create the game board.
     * We use a "custom" grid for keeping compatibility with the android 2.2 platform
     * GridLayout only usable in the Android 4.0 sdk
     *
     * @param gameBoard    LinearLayout containing the grid
     * @param _gameBoardDimension   Dimention of the grid
     * @param context  Context of the app
     */
    public Game( LinearLayout gameBoard, int[] _gameBoardDimension, Context context) {

        _gameBoard = gameBoard;
        _context = context;
        gameBoardDimension = _gameBoardDimension;

        for (int i = 0; i < gameBoardDimension[0]; i++){

            LinearLayout verticalLine = new LinearLayout(context);
            verticalLine.setOrientation(LinearLayout.VERTICAL);
            verticalLine.setWeightSum(gameBoardDimension[1]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
            verticalLine.setLayoutParams(params);
            verticalLine.setId(i);

            _gameBoard.addView(verticalLine);

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


    /**
     * Create new falling element
     */
    public void throwNewElement(){
        if (fallingElement != null){
            for (Square square : fallingElement._squaresList ){
                bottomSquareList.add(square);
            }
        }
        fallingElement = new Element(generator.nextInt(5),generator.nextInt(4),_gameBoard, _context);
    }

}