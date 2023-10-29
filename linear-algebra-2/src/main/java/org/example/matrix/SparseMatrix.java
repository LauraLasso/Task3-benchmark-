package org.example.matrix;

import org.example.Matrix;

import java.util.List;

public class SparseMatrix implements Matrix {
    private int size;
    private static List<CoordinateMatrix> coordinates;

    public SparseMatrix(int size, List<CoordinateMatrix> coordinates) {
        this.size = size;
        this.coordinates = coordinates;
    }

    //@Override
    public int getSize() {
        return size;
    }

    public List<CoordinateMatrix> getCoordinates() {
        return coordinates;
    }

    //@Override
    public long get(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Invalid indices");
        }
        return getValue(i, j); // Delega la implementación específica a los subtipos de matriz dispersa
    }
    // Devuelve la matriz densa de la matriz dispersa
    public static long getValue(int i, int j){
        for (CoordinateMatrix coordinate : coordinates) {
            if (coordinate.getRow() == i && coordinate.getColumn() == j) {
                return coordinate.getValue();
            }
        }
        return 0; // Si no se encuentra la coordenada, se devuelve 0
    }

    public static long[] multiplyWithVector(List<CoordinateMatrix> sparcematrix, long[] vector, int size) {
        if (sparcematrix == null || sparcematrix.isEmpty() || vector == null || vector.length != size) {
            throw new IllegalArgumentException("Invalid input");
        }

        long[] result = new long[size];
        for (CoordinateMatrix coordinate : sparcematrix) {
            int row = coordinate.getRow();
            int col = coordinate.getColumn();
            long value = coordinate.getValue();
            result[row] += value * vector[col];
        }

        return result;
    }
}
