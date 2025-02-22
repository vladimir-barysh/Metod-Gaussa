import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //чтение матриц из файлов
        String fileA = "matrixA.txt";
        String fileB = "matrixB.txt";
        List<double[]> matrixListA = new ArrayList<>();
        List<Double> matrixListB = new ArrayList<>();
        MatrixFromFile.readMatrixFromFile(matrixListA, fileA, matrixListB, fileB);

        double[][] matrixA = matrixListA.toArray(new double[0][]);

        double[] matrixB = new double[matrixListB.size()];
        for (int i = 0; i < matrixListB.size(); i++) {
            matrixB[i] = matrixListB.get(i);
        }
        //вычисление
        double[] result = MatrixOperations.gaussElimination(matrixA, matrixB);

        // Вывод матрицы
        System.out.println("Считанные матрицы:");
        int i = 0;
        for (double[] row : matrixA) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.print("|");
            System.out.print(matrixB[i]);
            System.out.println();
            ++i;
        }

        for (double num : result){
            System.out.print(num + " ");
        }
    }
}