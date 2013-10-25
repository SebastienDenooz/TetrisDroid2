package com.game.TetrisDroid2;

import android.content.Context;
import android.view.ViewManager;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;


/**
 * Element
 * Class to managing the falling form
 */
public class Element{


    private int _orientation;
    private int _formType;
    private LinearLayout _gameBoard;
    private Context _context;
    private int _positionX;
    private int _positionY = 0;

    public List<Square> _squaresList = new ArrayList<Square>();


    /**
     * Initialize the form
     * @param formType      Form to draw (L, T, I, S,...)
     * @param orientation   Orientation of the form
     * @param gameBoard     Game Board, where to draw the form
     * @param context       Context of the app.
     */
    public Element(int formType, int orientation, LinearLayout gameBoard, Context context){
        _orientation = orientation;
        _formType = formType;
        _gameBoard = gameBoard;
        _context = context;
        _positionX = Math.round(_gameBoard.getChildCount()/2);
        drawSquare();
    }


    /**
     * Draw the form on the game board
     * @return True if we can draw the form, false if not.
     */
    private boolean drawSquare(){

        /**
         * Prepare the new element
         *
         * TODO: Replace this bullshit architecture with correct data structure in elements.java
         */
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

        /**
         * Check if we have place to put it
         */
        for (int x = 0;x<matrix.length;x++){
            for (int y = 0;y<matrix[x].length;y++){

                if (matrix[x][y] == 1){

                    LinearLayout column = (LinearLayout) _gameBoard.getChildAt(positionX+x);

                    if (column == null){ return false; }

                    LinearLayout gridCase = (LinearLayout) column.getChildAt(_positionY+y);
                    Square tmpSquare = (Square) gridCase.getChildAt(0);

                    if ( gridCase.getChildCount() > 0 & !_squaresList.contains(tmpSquare)){
                        return false;
                    }

                }

            }
        }

        /**
         * Remove and clear the old square's
         */
        for (Square square : _squaresList ){
            _gameBoard.removeView(square);
            ((ViewManager) square.getParent()).removeView(square);
            square = null;
        }
        _squaresList.clear();

        /**
         * Create the new one's
         */
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

    /**
     * Go down the form from 1 on the y axis.
     * @return  True if the element as ben downed. False if not.
     */
    public boolean downElement(){

        boolean canFall = true;

        /**
         * Test if we can down the element before
         */
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
        if (_orientation > 3 ){ _orientation = 0; }
        return drawSquare();
    }


    public boolean rotateRight(){
        _orientation--;
        if (_orientation < 0 ){ _orientation = 3; }
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