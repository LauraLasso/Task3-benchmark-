package org.example;

import org.example.matrix.CompressedColumnMatrix;
import org.example.matrix.CompressedRowMatrix;
import org.example.matrix.DenseMatrix;
import org.example.matrix.SparseMatrix;
import org.example.operators.MatrixMultiplication;

import java.io.IOException;

public class Controller {

    MatrixLoader matrixLoader;
    MatrixMultiplication DenseMultiplier;
    MatrixMultiplication SparseMultiplier;

    public Controller(MatrixLoader loader, MatrixMultiplication multiplier, MatrixMultiplication SparseMultiplier) {
        this.matrixLoader = loader;
        this.DenseMultiplier = multiplier;
        this.SparseMultiplier = SparseMultiplier;
    }

    public void start(String filePath) throws IOException {
        try {
            SparseMatrix matrix = matrixLoader.loadSparseMatrix(filePath);
            DenseMatrix denseMatrix = matrixLoader.loadDenseMatrix(filePath);

            System.out.println("Results of the original sparse matrix: ");

            // Convierte la matriz a formato CCS
            CompressedColumnMatrix ccsMatrix = MatrixTransform.transformCCS(matrix);

            // Convierte la matriz a formato CRS
            CompressedRowMatrix crsMatrix = MatrixTransform.transformCRS(matrix);

            // Realiza la multiplicación de la matriz CCS consigo misma
            long startTimeDense = System.currentTimeMillis();
            Matrix ccsCrsResult = SparseMultiplier.multiply(crsMatrix, ccsMatrix);
            long endTimeDense = System.currentTimeMillis();
            long totalTimeDense = endTimeDense - startTimeDense;
            System.out.println("Total time taken for multiplication of sparse matrices in CCS*CRS format:: " + totalTimeDense + " milisegundos");

            // Resultados CRS*CCS
            // Validamos la multiplicacion
            System.out.println("Sparse Matrix Multiplication Validation in CCS*CRS Format: ");
            System.out.println(SparseMultiplier.checkMultiply(crsMatrix, ccsMatrix, ccsCrsResult));

            // Realiza la multiplicación de la matriz densa consigo misma
            System.out.println("Dense Matrix Multiplication Result: ");
            long startTimeSparse = System.currentTimeMillis();
            Matrix denseResult2 = DenseMultiplier.multiply(denseMatrix, denseMatrix);
            long endTimeSparse = System.currentTimeMillis();
            long totalTimeSparse = endTimeSparse - startTimeSparse;
            System.out.println("Total Time of Dense Matrix Multiplication: " + totalTimeSparse + " miliseconds");

            System.out.println("Validation of dense matrix multiplication: ");
            // Validamos la multiplicacion
            System.out.println(DenseMultiplier.checkMultiply(denseMatrix, denseMatrix, denseResult2));


            // Aquí puedes trabajar con los resultados como desees
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
