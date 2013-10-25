package com.game.TetrisDroid2;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;


/**
 * New object based on ImageView for representing the Squares
 */
public class Square extends ImageView{


    public Square(Context context){
        super(context);
        this.setBackgroundColor(Color.WHITE);
    }

    /**
     * Creator for setting the color arg.
     * @param context
     * @param color
     */
    public Square(Context context, int color){
        super(context);
        this.setBackgroundColor(color);
    }


}