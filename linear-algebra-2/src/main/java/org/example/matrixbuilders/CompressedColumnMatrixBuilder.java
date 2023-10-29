package org.example.matrixbuilders;

import org.example.MatrixBuilder;
import org.example.matrix.CompressedColumnMatrix;
import org.example.matrix.CoordinateMatrix;

import java.util.Collections;
import java.util.List;

public class CompressedColumnMatrixBuilder{
    private long[] values;
    private int[] rows;
    private int[] col_ptr;
    private int size;

    public CompressedColumnMatrixBuilder(int size) {
        //super(size);
        this.size = size;
    }

    public void set(List<CoordinateMatrix> coordinates) {
        Collections.sort(coordinates, (c1, c2) -> {
            if (c1.getCol() != c2.getCol()) {
                return c1.getCol() - c2.getCol();
            } else {
                return c1.getRow() - c2.getRow();
            }
        });

        int nnz = coordinates.size();
        values = new long[nnz];
        rows = new int[nnz];
        col_ptr = new int[size + 1];

        int currentCol = -1;
        int index = 0;
        for (CoordinateMatrix coord : coordinates) {
            int col = coord.getCol();
            int row = coord.getRow();
            long value = coord.getValue();

            values[index] = value;
            rows[index] = row;

            if (col != currentCol) {
                for (int k = currentCol + 1; k <= col; k++) {
                    col_ptr[k] = index;
                }
                currentCol = col;
            }
            index++;
        }
        for (int k = currentCol + 1; k <= size; k++) {
            col_ptr[k] = nnz;
        }
    }

    public CompressedColumnMatrix get() {
        return new CompressedColumnMatrix(size, values, rows, col_ptr);
    }
}
