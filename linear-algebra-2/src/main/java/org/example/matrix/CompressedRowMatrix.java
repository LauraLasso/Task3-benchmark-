package org.example.matrix;

import org.example.Matrix;

public class CompressedRowMatrix implements Matrix {
    public int size;
    public long[] values;
    public int[] columns;
    public int[] rowPointers;

    public CompressedRowMatrix(int size, long[] values, int[] columns, int[] rowPointers) {
        this.size = size;
        this.values = values;
        this.columns = columns;
        this.rowPointers = rowPointers;
    }

    /*
    public long get(int i, int j) {
        // Implementa la lógica para obtener el valor en la posición (i, j)
        return 0; // Cambia esto con la lógica real
    }

     */

    @Override
    public int getSize() {
        return size;
    }

    public long get(int i, int j) {
        // Implement the logic to obtain the value in CRS
        int rowStart = rowPointers[i];
        int rowEnd = rowPointers[i + 1];

        for (int k = rowStart; k < rowEnd; k++) {
            if (columns[k] == j) {
                return values[k];
            }
        }
        return 0;
    }

    // TODO
    public long[] multiply(long[] vector) {
        if (size != vector.length) {
            throw new IllegalArgumentException("Incompatible dimensions");
        }
        long[] result = new long[size];
        for (int i = 0; i < size; i++) {
            int start = rowPointers[i];
            int end = rowPointers[i + 1];
            for (int j = start; j < end; j++) {
                result[i] += values[j] * vector[columns[j]];
            }
        }
        return result;
    }

    public int[] getRowPointers() {
        return rowPointers;
    }

    public int[] getColumns() {
        return columns;
    }

    public long[] getValues() {
        return values;
    }
}
