package org.example.matrix;

import org.example.Matrix;
import java.util.HashMap;
import java.util.Map;

public class DenseMatrix implements Matrix {
    long[][] matrix;

    public DenseMatrix(long[][] matrix) {
        this.matrix = matrix;
    }

    // size
    //@Override
    public int getSize() {
        return matrix.length;
    }

    public long[][] getMatrix() {
        return matrix;
    }

    public long[] multiplyVector(long[] vector) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (vector.length != n) {
            throw new IllegalArgumentException("Incompatible dimensions for matrix and vector multiplication");
        }
        long[] result = new long[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    //@Override
    public long get(int i, int j) {
        return matrix[i][j];
    }

    public int getRows() {
        return matrix[0].length;
    }

    public int getColumns() {
        return matrix[1].length;
    }

    public long getValue(int i, int k) {
        return matrix[i][k];
    }
}

