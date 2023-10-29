package org.example.matrixbuilders;

import org.example.MatrixBuilder;
import org.example.matrix.CoordinateMatrix;
import org.example.matrix.SparseMatrix;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixBuilder implements MatrixBuilder {

    protected List<CoordinateMatrix> coordinates;
    private int size;
    public SparseMatrixBuilder(int size) {
        this.size = size;
        this.coordinates = new ArrayList<>();
    }

    //@Override
    public SparseMatrix get() {
        return new SparseMatrix(size, coordinates);
    }

    public void set(int i, int j, long value) {
        coordinates.add(new CoordinateMatrix(i,j,value));
    }

}
