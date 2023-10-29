package org.example;

import org.example.matrix.CompressedColumnMatrix;
import org.example.matrix.CompressedRowMatrix;
import org.example.matrix.SparseMatrix;
import org.example.matrixbuilders.CompressedColumnMatrixBuilder;
import org.example.matrixbuilders.CompressedRowMatrixBuilder;

public class MatrixTransform {
    public static CompressedColumnMatrix transformCCS(SparseMatrix matrix){
        CompressedColumnMatrixBuilder ccsBuilder = new CompressedColumnMatrixBuilder(matrix.getSize());
        ccsBuilder.set(matrix.getCoordinates());
        CompressedColumnMatrix ccsMatrix = ccsBuilder.get();
        return ccsMatrix;
    }

    public static CompressedRowMatrix transformCRS(SparseMatrix matrix){
        CompressedRowMatrixBuilder crsBuilder = new CompressedRowMatrixBuilder(matrix.getSize());
        crsBuilder.set(matrix.getCoordinates());
        CompressedRowMatrix crsMatrix = crsBuilder.get();
        return crsMatrix;
    }
}
