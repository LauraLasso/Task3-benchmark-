package org.example.matrix;

import org.example.Matrix;

public class CompressedColumnMatrix implements Matrix {
    int size;
    public long[] values;
    public int[] rows;
    public int[] colPointers;

    public CompressedColumnMatrix(int size, long[] values, int[] rows, int[] colPointers) {
        this.size = size;
        this.values = values;
        this.rows = rows;
        this.colPointers = colPointers;
    }

    /*
    public long get(int i, int j) {
        // Implementa la lógica para obtener el valor en la posición (i, j)
        return 0; // Cambia esto con la lógica real
    }

     */

    public long get(int i, int j) {
        // Implementa la lógica para obtener el valor en CCS
        int start = colPointers[j];
        int end = colPointers[j + 1];

        for (int k = start; k < end; k++) {
            if (rows[k] == i) {
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
        for (int j = 0; j < size; j++) {
            if (j + 1 < colPointers.length) {
                int start = colPointers[j];
                int end = colPointers[j + 1];
                if (end <= rows.length) {
                    for (int i = start; i < end; i++) {
                        if (rows[i] < size) {
                            result[rows[i]] += values[i] * vector[j];
                        }
                    }
                }
            }
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public int[] getColPointers() {
        return colPointers;
    }

    public int[] getRows() {
        return rows;
    }

    public long[] getValues() {
        return values;
    }
}
