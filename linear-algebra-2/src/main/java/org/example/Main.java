package org.example;
import org.example.operators.matrixmultiplication.DenseMatrixMultiplication;
import org.example.operators.matrixmultiplication.SparseMatrixMultiplication;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "C:/Users/laura/IdeaProjects/linear-algebra-2/src/main/resources/1138_bus.mtx";
        new Controller(new MatrixLoader(), new DenseMatrixMultiplication(), new SparseMatrixMultiplication()).start(filePath);
    }
}
