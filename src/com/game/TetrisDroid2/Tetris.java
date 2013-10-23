package com.game.TetrisDroid2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tetris extends Activity implements Elements {
    /**
     * Called when the activity is first created.
     */
    private Handler handler = new Handler();
    Random generator = new Random();
    LinearLayout gameBoard;
    List<Square> fallingSquareList = new ArrayList<Square>();
    List<Square> bottomSquareList = new ArrayList<Square>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.gameBoard = (LinearLayout) this.findViewById(R.id.gameBoard);
        Game theGame = new Game();
        theGame.main(gameBoard, this);
        champi();
        handler.postDelayed(heartBeat, 10);
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
        if (fallingSquareList.isEmpty()){
            tElement element = new tElement();
            int orientation = generator.nextInt(4);
            int[][] matrix = element.matrix[orientation];
            System.out.println("New square ?");
            for (int i = 0;i<matrix.length;i++){
                for (int j = 0;j<matrix[i].length;j++){
                    System.out.println(matrix[i][j]);
                    if (matrix[i][j] == 1){

                        int startPosition = Math.round(gameBoard.getChildCount()/2);
                        Square newSquare = new Square(this, element.color);
                        LinearLayout PosX = (LinearLayout) gameBoard.getChildAt(startPosition);
                        LinearLayout PosXY = (LinearLayout) PosX.getChildAt(0+j);
                        if ( PosXY.getChildCount() <1 ){
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
                            newSquare.setLayoutParams(params);
                            newSquare.setOrientation(orientation);
                            PosXY.addView(newSquare);
                            fallingSquareList.add(newSquare);
                        }
                    }
                }
            }
        }

    }

    private boolean downElement(){
        if (false){
            throwNewElement();
        }
        return true;
    }

    private void rotateElementLeft(){

    }

    private void rotateElementRight(){

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
        throwNewElement();
        //redrawGameBoard();
        handler.postDelayed(this, 10);
        }
    };

}
