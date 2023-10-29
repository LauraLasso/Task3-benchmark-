package org.example.matrixbuilders;

import org.example.matrix.CompressedRowMatrix;
import org.example.matrix.CoordinateMatrix;

import java.util.Collections;
import java.util.List;

public class CompressedRowMatrixBuilder{
    private long[] values;
    private int[] columns;
    private int[] row_ptr;
    private int size;

    public CompressedRowMatrixBuilder(int size) {
        //super(size);
        this.size = size;
    }

    public void set(List<CoordinateMatrix> coordinates) {
        Collections.sort(coordinates, (c1, c2) -> {
            if (c1.getRow() != c2.getRow()) {
                return c1.getRow() - c2.getRow();
            } else {
                return c1.getCol() - c2.getCol();
            }
        });

        int nnz = coordinates.size();
        values = new long[nnz];
        columns = new int[nnz];
        row_ptr = new int[size + 1];

        int currentRow = -1;
        int index = 0;
        for (CoordinateMatrix coord : coordinates) {
            int row = coord.getRow();
            int col = coord.getCol();
            long value = coord.getValue();

            values[index] = value;
            columns[index] = col;

            if (row != currentRow) {
                for (int k = currentRow + 1; k <= row; k++) {
                    row_ptr[k] = index;
                }
                currentRow = row;
            }
            index++;
        }
        for (int k = currentRow + 1; k <= size; k++) {
            row_ptr[k] = nnz;
        }
    }

    public CompressedRowMatrix get() {
        return new CompressedRowMatrix(size, values, columns, row_ptr);
    }
}
