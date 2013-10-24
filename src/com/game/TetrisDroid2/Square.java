package com.game.TetrisDroid2;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

public class Square extends ImageView implements Comparable<Square>{

    public static boolean FALLING = true;

    private int _posX = 0;
    private int _posY = 0;

    public Square(Context context){
        super(context);
        this.setBackgroundColor(Color.WHITE);
    }

    public Square(Context context, int color){
        super(context);
        this.setBackgroundColor(color);
    }

    public int getPosX(){
        return _posX;
    }

    public void setPosX(int posX){
        _posX = posX;
    }

    public int getPosY(){
        return _posY;
    }

    public void setPosY(int posY){
        _posY =  posY;
    }

    public int compareTo(Square otherSquare){
        return this._posY - otherSquare._posY;
    }

}