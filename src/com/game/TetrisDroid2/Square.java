package com.game.TetrisDroid2;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

public class Square extends ImageView{

    private boolean _isFalling = true;
    private int _squareColor = 0;

    public Square(Context context){
        super(context);
        _squareColor = Color.WHITE;
    }

    public Square(Context context, int color){
        super(context);
        _squareColor = color;
    }

    public void setStateFalling(){
        _isFalling = true;
    }

    public void setStateImmobile(){
        _isFalling = false;
    }

    public boolean getState(){
        return _isFalling;
    }

    public void setSquareColor(int color){
        _squareColor = color;
    }

    public int getSquareColor(){
        return _squareColor;
    }

}