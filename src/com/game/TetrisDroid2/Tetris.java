package com.game.TetrisDroid2;

import android.app.Activity;
import android.os.Bundle;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addListenerOnButton();

        this.gameBoard = (LinearLayout) this.findViewById(R.id.gameBoard);
        theGame = new Game(gameBoard, this);
        ///theGame.handler.postDelayed(theGame.heartBeat, 1000);
    }

    public void onStop(){
        super.onStop();
        theGame.handler.removeCallbacks(theGame.heartBeat);
    }

//
//    private void removeFullLines(){
//
//    }
//
//
//
//    private void throwNewElement(){
//        if (fallingSquareList.isEmpty()){
//            int elementType = generator.nextInt(5);
//            Elements element = new Elements();
//            switch (elementType){
//                case 0: element.initializeT();
//                    break;
//                case 1: element.initializeI();
//                    break;
//                case 2: element.initializeL();
//                    break;
//                case 3: element.initializeO();
//                    break;
//                case 4: element.initializeS();
//                    break;
//                default: element.initializeS();
//                    break;
//            }
//            int orientation = generator.nextInt(4);
//            int[][] matrix = element.matrix[orientation];
//            int startPosition = Math.round(gameBoard.getChildCount()/2)-matrix.length;
//
//            for (int x = 0;x<matrix.length;x++){
//                for (int j = 0;j<matrix[x].length;j++){
//
//                    if (matrix[x][j] == 1){
//
//
//                        Square newSquare = new Square(this, element.color);
//
//                        LinearLayout PosX = (LinearLayout) gameBoard.getChildAt(startPosition+x);
//                        LinearLayout PosXY = (LinearLayout) PosX.getChildAt(j);
//
//                        if ( PosXY.getChildCount() < 1 ){
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
//                            newSquare.setLayoutParams(params);
//                            newSquare.setOrientation(orientation);
//                            PosXY.addView(newSquare);
//                            fallingSquareList.add(newSquare);
//                        }
//
//                    }
//                }
//            }
//            int size = fallingSquareList.size();
//            Log.v(TAG, "Falling element = "+size);
//        }
//
//    }
//
//    private boolean elementCanFall(){
//
//        Collections.sort(fallingSquareList);
//        boolean elementCanFall = true;
//
//        for (Square square : fallingSquareList ){
//            LinearLayout gridCase = (LinearLayout) square.getParent();
//            LinearLayout column = (LinearLayout) gridCase.getParent();
//            Log.v(TAG,column.toString());
//            int x = gameBoard.indexOfChild(column);
//            int y = column.indexOfChild(gridCase);
//
//            if (y < 19){
//                LinearLayout nextGridCase = (LinearLayout) column.getChildAt(y+1);
//                Square nextSquare = (Square) nextGridCase.getChildAt(0);
//                if(fallingSquareList.contains(nextSquare)){
//                    Log.v(TAG,"Stop falling...");
//                    elementCanFall = false;
//                    bottomSquareList.add(square);
//                }
//            }
//        }
//        if (!elementCanFall){
//            fallingSquareList.clear();
//        }
//        return elementCanFall;
//    }
//
//
//    private void downElement(){
//        Collections.sort(fallingSquareList);
//        Collections.reverseOrder();
//        for (Square square : fallingSquareList ){
//
//            LinearLayout gridCase = (LinearLayout) square.getParent();
//            LinearLayout column = (LinearLayout) gridCase.getParent();
//
//            int y = column.indexOfChild(gridCase);
//            Log.v(TAG, "Y = "+y);
//            Log.v(TAG, "New Y = "+(y+1));
//
//            if (y<19){
//                LinearLayout nextGridCase = (LinearLayout) column.getChildAt(y+1);
//                gridCase.removeView(square);
//                nextGridCase.addView(square);
//            }
//
//        }
//    }
//
//
//    private void redrawGameBoard(){
//        for (int x = 0; x < gameBoard.getChildCount();x++){
//            LinearLayout column = (LinearLayout) gameBoard.getChildAt(x);
//            for (int y = column.getChildCount()-2;y>=0;y--){
//                LinearLayout gridCase = (LinearLayout) column.getChildAt(y);
//                    gridCase.removeViewAt(0);
//            }
//        }
//    }


    public void addListenerOnButton() {
        ImageButton butn = (ImageButton) findViewById(R.id.btnTurnRight);
        ImageButton butnLeft = (ImageButton) findViewById(R.id.btnTurnLeft);
        ImageButton straffLeft = (ImageButton) findViewById(R.id.btnGoLeft);
        ImageButton straffRight = (ImageButton) findViewById(R.id.btnGoRight);
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element theElement = theGame.fallingElement;
                boolean isDown = theElement.downElement();
                System.out.println(isDown);
                if (!isDown){
                    System.out.println(theGame);
                    theGame.throwNewElement();
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
}
