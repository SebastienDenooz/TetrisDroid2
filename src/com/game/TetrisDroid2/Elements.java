package com.game.TetrisDroid2;

import android.graphics.Color;

public class Elements {

    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    public static int[][][] matrix;
    public static int color;

    public void initializeT(){
        int[][][]_matrix =  {
                {{1,1,1},{0,1,0}}, // NORTH
                {{0,1,0},{1,1,1}}, // SOUTH
                {{0,1},{1,1},{0,1}}, // EAST
                {{1,0},{1,1},{1,0}} // WEST
        };
        matrix = _matrix;
        color = Color.WHITE;

    }

    public void initializeI(){
        int[][][] _matrix ={
                {{1},{1},{1},{1}}, // NORTH
                {{1},{1},{1},{1}}, // SOUTH
                {{1,1,1,1}}, // EAST
                {{1,1,1,1}} // WEST
        };
        matrix = _matrix;
        color = Color.RED;
    }

    public void initializeS(){
        int[][][] _matrix ={
                {{0,1,1},{1,1,0}},
                {{0,1},{1,1},{1,0}},
                {{1,1,0},{0,1,1}},
                {{1,0},{1,1},{0,1}}
        };
        matrix = _matrix;
        color = Color.YELLOW;
    }

    public void initializeO(){

        int[][][] _matrix ={
                {{1,1},{1,1}},
                {{1,1},{1,1}},
                {{1,1},{1,1}},
                {{1,1},{1,1}},
        };
        matrix = _matrix;
        color = Color.CYAN;
    }

    public void initializeL(){

        int[][][] _matrix ={
                {{0,1},{0,1},{1,1}},
                {{1,1},{1,0},{1,0}},
                {{0,0,1},{1,1,1}},
                {{1,1,1},{0,0,1}}
        };
        matrix = _matrix;
        color = Color.GREEN;
    }
}