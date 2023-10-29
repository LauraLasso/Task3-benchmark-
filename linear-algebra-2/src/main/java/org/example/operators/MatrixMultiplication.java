package org.example.operators;
import org.example.Matrix;

public interface MatrixMultiplication {
    Matrix multiply(Matrix A, Matrix B);
    // Comprobar si la multiplicacion esta bien
    Boolean checkMultiply(Matrix A, Matrix B, Matrix C);
    long[] generateVector(int size);
}
