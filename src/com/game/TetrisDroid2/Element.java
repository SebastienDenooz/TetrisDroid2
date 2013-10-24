package com.game.TetrisDroid2;


import android.content.Context;
import android.widget.LinearLayout;

import java.util.List;

public class Element{


    private List<Square> _squaresList;
    private int _orientation;
    private int _formType;
    private LinearLayout _gameBoard;
    private Context _context;


    public Element(int formType, int orientation, LinearLayout gameBoard, Context context){
        _orientation = orientation;
        _formType = formType;
        _gameBoard = gameBoard;
        _context = context;

        Elements form = new Elements();
        switch (formType){
            case 0: form.initializeT();
                break;
            case 1: form.initializeI();
                break;
            case 2: form.initializeL();
                break;
            case 3: form.initializeO();
                break;
            case 4: form.initializeS();
                break;
            default: form.initializeS();
                break;
        }
        int[][] matrix = form.matrix[orientation];
        int startPosition = Math.round(gameBoard.getChildCount()/2)-matrix.length;

        for (int x = 0;x<matrix.length;x++){
            for (int j = 0;j<matrix[x].length;j++){

                if (matrix[x][j] == 1){

                    LinearLayout PosX = (LinearLayout) gameBoard.getChildAt(startPosition+x);
                    LinearLayout PosXY = (LinearLayout) PosX.getChildAt(j);

                    if ( PosXY.getChildCount() < 1 ){

                        Square newSquare = new Square(_context, form.color);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
                        newSquare.setLayoutParams(params);
                        PosXY.addView(newSquare);
                        this._squaresList.add(newSquare);
                    }

                }
            }
        }
        int size = _squaresList.size();
    }


    public boolean downElement(){
        return true;
    }


    public boolean rotateLeft(){
        return true;
    }


    public boolean rotateRight(){
        return true;
    }


    private void strafElementLeft(){

    }


    private void strafElementRight(){

    }
}