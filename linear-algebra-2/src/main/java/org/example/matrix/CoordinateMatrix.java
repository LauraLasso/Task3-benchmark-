package org.example.matrix;

import java.util.List;

public class CoordinateMatrix{
    private int row;
    private int col;
    private long value;


    public CoordinateMatrix(int row, int col, long value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() { return col; }

    public int getCol() {
        return col;
    }

    public long getValue() {
        return value;
    }

}
