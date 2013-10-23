package com.game.TetrisDroid2;

import android.graphics.Color;

public interface Elements {

    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    public static final class tElement {

        public int[][][] matrix ={
                {{1,1,1},{0,1,0}}, // NORTH
                {{0,1,0},{1,1,1}}, // SOUTH
                {{0,1},{1,1},{0,1}}, // EAST
                {{1,0},{1,1},{1,0}} // WEST
        };
        public int color = Color.WHITE;

    }

    public static final class iElement {

        public int[][][] matrix ={
                {{1},{1},{1},{1}}, // NORTH
                {{1},{1},{1},{1}}, // SOUTH
                {{1,1,1,1}}, // EAST
                {{1,1,1,1}} // WEST
        };
        public int color = Color.RED;

    }

    public static final class sElement {

        public int[][][] matrix ={
                {{0,1,1},{1,1,0}},
                {{0,1},{1,1},{1,0}},
                {{1,1,0},{0,1,1}},
                {{1,0},{1,1},{0,1}}
        };
        public int color = Color.YELLOW;

    }


    public static final class oElement {

        public int[][][] matrix ={
                {{1,1},{1,1}},
                {{1,1},{1,1}},
                {{1,1},{1,1}},
                {{1,1},{1,1}},
        };
        public int color = Color.CYAN;

    }


    public static final class lElement {

        public int[][][] matrix ={
                {{0,1},{0,1},{1,1}},
                {{1,1},{1,0},{1,0}},
                {{0,0,1},{1,1,1}},
                {{1,1,1},{0,0,1}}
        };
        public int color = Color.GREEN;

    }

}