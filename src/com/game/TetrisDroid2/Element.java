package com.game.TetrisDroid2;


import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Element{


    public List<Square> _squaresList = new ArrayList<Square>();
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
                        _squaresList.add(newSquare);
                    }

                }
            }
        }
    }


    public boolean downElement(){
        boolean canFall = true;
        for (Square square : _squaresList ){

            LinearLayout gridCase = (LinearLayout) square.getParent();
            LinearLayout column = (LinearLayout) gridCase.getParent();

            int y = column.indexOfChild(gridCase);

            if (y<19){
                LinearLayout nextGridCase = (LinearLayout) column.getChildAt(y+1);
                if (nextGridCase.getChildCount() > 0){
                    Square presentSquare = (Square) nextGridCase.getChildAt(0);
                    if (!(_squaresList.contains(presentSquare))){
                        System.out.println("Can NOT fall...");
                        canFall = false;
                    }else{
                        System.out.println("Ok, can fall...");
                    }
                }
            }else{
                canFall = false;

            }

        }
        if (canFall){
            for (Square square : _squaresList ){

                LinearLayout gridCase = (LinearLayout) square.getParent();
                LinearLayout column = (LinearLayout) gridCase.getParent();

                int y = column.indexOfChild(gridCase);

                if (y<19){
                    LinearLayout nextGridCase = (LinearLayout) column.getChildAt(y+1);
                    gridCase.removeView(square);
                    nextGridCase.addView(square);
                }

            }
        }

        return canFall;
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