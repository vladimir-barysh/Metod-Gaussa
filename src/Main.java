import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<int[]> matrixListA = new ArrayList<>();
        MatrixFromFile.readMatrixFromFile(matrixListA, "matrixA.txt");

        // Преобразуем список в двумерный массив
        int[][] matrixA = matrixListA.toArray(new int[0][]);

        //вторая матрица
        List<int[]> matrixListB = new ArrayList<>();
        MatrixFromFile.readMatrixFromFile(matrixListB, "matrixB.txt");
        int[][] matrixB = matrixListB.toArray(new int[0][]);

        //сумма двух матриц
        int[][] matrixC = MatrixOperations.matrixSum(matrixA, matrixB);

        // Вывод матрицы
        System.out.println("Считанная матрица:");
        for (int[] row : matrixC) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}