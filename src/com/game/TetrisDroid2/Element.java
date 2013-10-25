package com.game.TetrisDroid2;


import android.content.Context;
import android.view.ViewManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Element{


    public List<Square> _squaresList = new ArrayList<Square>();
    private int _orientation;
    private int _formType;
    private LinearLayout _gameBoard;
    private Context _context;
    private int _positionX;
    private int _positionY = 0;


    public Element(int formType, int orientation, LinearLayout gameBoard, Context context){
        _orientation = orientation;
        _formType = formType;
        _gameBoard = gameBoard;
        _context = context;
        _positionX = Math.round(_gameBoard.getChildCount()/2);
        drawSquare();
    }


    private boolean drawSquare(){
        Elements form = new Elements();
        switch (_formType){
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
        int[][] matrix = form.matrix[_orientation];
        int positionX = _positionX-matrix.length;
        for (int x = 0;x<matrix.length;x++){
            for (int y = 0;y<matrix[x].length;y++){
                if (matrix[x][y] == 1){
                    LinearLayout column = (LinearLayout) _gameBoard.getChildAt(positionX+x);
                    System.out.println(column);
                    if (column == null){ return false; }
                    LinearLayout gridCase = (LinearLayout) column.getChildAt(_positionY+y);
                    Square tmpSquare = (Square) gridCase.getChildAt(0);
                    if ( gridCase.getChildCount() > 0 & !_squaresList.contains(tmpSquare)){
                        return false;
                    }

                }
            }
        }


        for (Square square : _squaresList ){
            _gameBoard.removeView(square);
            ((ViewManager) square.getParent()).removeView(square);
            square = null;
        }
        _squaresList.clear();

        for (int x = 0;x<matrix.length;x++){
            for (int y = 0;y<matrix[x].length;y++){

                if (matrix[x][y] == 1){

                    LinearLayout column = (LinearLayout) _gameBoard.getChildAt(positionX+x);
                    LinearLayout gridCase = (LinearLayout) column.getChildAt(_positionY+y);

                    Square newSquare = new Square(_context, form.color);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1);
                    newSquare.setLayoutParams(params);
                    gridCase.addView(newSquare);
                    _squaresList.add(newSquare);

                }
            }
        }
        return true;

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
                        canFall = false;
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
            _positionY++;
        }
        return canFall;
    }


    public boolean rotateLeft(){
        _orientation++;
        if (_orientation > 3 ) _orientation = 0;
        return drawSquare();
    }


    public boolean rotateRight(){
        _orientation--;
        if (_orientation < 0 ) _orientation = 4;
        return drawSquare();
    }


    public boolean strafElementLeft(){
        _positionX--;
        boolean straffResult = drawSquare();
        if (!straffResult) _positionX++;
        return straffResult;

    }


    public boolean strafElementRight(){
        _positionX++;
        boolean straffResult = drawSquare();
        if (!straffResult) _positionX--;
        return straffResult;

    }
}