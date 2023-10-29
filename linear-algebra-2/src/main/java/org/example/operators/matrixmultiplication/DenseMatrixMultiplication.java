package org.example.operators.matrixmultiplication;

import org.example.Matrix;
import org.example.matrix.DenseMatrix;
import org.example.operators.MatrixMultiplication;

import java.util.Arrays;
import java.util.Random;

public class DenseMatrixMultiplication implements MatrixMultiplication {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        if (!(a instanceof DenseMatrix) || !(b instanceof DenseMatrix)) {
            throw new IllegalArgumentException("Incompatible matrix types");
        }

        DenseMatrix denseA = (DenseMatrix) a;
        DenseMatrix denseB = (DenseMatrix) b;

        int n = denseA.getRows();
        int m = denseA.getColumns();
        int p = denseB.getColumns();
        long[][] result = new long[n][p];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    result[i][j] += denseA.getValue(i, k) * denseB.getValue(k, j);
                }
            }
        }
        return new DenseMatrix(result);
    }


    @Override
    public Boolean checkMultiply(Matrix A, Matrix B, Matrix C) {
        if (!(A instanceof DenseMatrix) || !(B instanceof DenseMatrix) || !(C instanceof DenseMatrix)) {
            throw new IllegalArgumentException("Incompatible matrix types");
        }

        DenseMatrix denseA = (DenseMatrix) A;
        DenseMatrix denseB = (DenseMatrix) B;
        DenseMatrix denseC = (DenseMatrix) C;

        // Verificar la igualdad A*(B*v) = C*v
        long[] v = generateVector(denseA.getRows());

        long[] Bv = denseB.multiplyVector(v);
        long[] AvBv = denseA.multiplyVector(Bv);
        long[] Cv = denseC.multiplyVector(v);

        boolean condition2 = Arrays.equals(AvBv, Cv);

        return condition2;
    }



    @Override
    public long[] generateVector(int size) {
        Random random = new Random();
        long[] v = new long[size]; // 'size' es el tamaño del vector
        for (int i = 0; i < size; i++) {
            v[i] = random.nextLong();
        }
        return v;
    }
}
