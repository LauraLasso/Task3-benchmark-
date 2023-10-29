package org.example.operators.matrixmultiplication;

import org.example.Matrix;
import org.example.matrix.CompressedColumnMatrix;
import org.example.matrix.CompressedRowMatrix;
import org.example.matrix.SparseMatrix;
import org.example.matrixbuilders.SparseMatrixBuilder;
import org.example.operators.MatrixMultiplication;

import java.util.Arrays;
import java.util.Random;

public class SparseMatrixMultiplication implements MatrixMultiplication {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        if (!(a instanceof CompressedRowMatrix) || !(b instanceof CompressedColumnMatrix)) {
            throw new IllegalArgumentException("Incompatible matrix types");
        }

        CompressedRowMatrix aRow = (CompressedRowMatrix) a;
        CompressedColumnMatrix bColumn = (CompressedColumnMatrix) b;

        SparseMatrixBuilder builder = new SparseMatrixBuilder(a.getSize());
        for (int i = 0; i < a.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                int ii = aRow.getRowPointers()[i];
                int iEnd = aRow.getRowPointers()[i + 1];
                int jj = bColumn.getColPointers()[j];
                int jEnd = bColumn.getColPointers()[j + 1];
                long s = 0;
                while (ii < iEnd && jj < jEnd) {
                    int aa = aRow.getColumns()[ii];
                    int bb = bColumn.getRows()[jj];
                    if (aa == bb) {
                        s += aRow.getValues()[ii] * bColumn.getValues()[jj];
                        ii++;
                        jj++;
                    } else if (aa < bb) {
                        ii++;
                    } else {
                        jj++;
                    }
                }
                if (s != 0) {
                    builder.set(i, j, s);
                }
            }
        }
        return builder.get();
    }

    @Override
    public Boolean checkMultiply(Matrix A, Matrix B, Matrix C) {
            if (!(B instanceof CompressedColumnMatrix) || !(A instanceof CompressedRowMatrix) || !(C instanceof SparseMatrix)) {
                throw new IllegalArgumentException("Incompatible matrix types");
            }
            CompressedColumnMatrix compressedB = (CompressedColumnMatrix) B;
            CompressedRowMatrix compressedA = (CompressedRowMatrix) A;
            SparseMatrix sparseC = (SparseMatrix) C;

            // Verificar la igualdad A*(B*v) = C*v
            // Generar un vector aleatorio
            long[] v = generateVector(compressedB.getSize());

            long[] Bv = compressedB.multiply(v);
            long[] AvBv = compressedA.multiply(Bv);
            long[] Cv = SparseMatrix.multiplyWithVector(sparseC.getCoordinates(), v, sparseC.getSize());

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
