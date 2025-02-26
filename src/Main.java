import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        //Чтение матриц из файлов

        String fileA = "matrixA.txt";
        String fileB = "matrixB.txt";
        String fileRes = "result.txt";

        List<double[]> matrixListA = new ArrayList<>();
        List<Double> matrixListB = new ArrayList<>();

        MatrixFromFile.readMatrixFromFile(matrixListA, fileA, matrixListB, fileB);

        double[][] matrixA = matrixListA.toArray(new double[0][]);
        int n = matrixListB.size();
        double[][] matrixACopy = new double[n][n];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j) {
                matrixACopy[i][j] = matrixA[i][j];
            }
        }
        double[] matrixB = new double[n];
        for (int i = 0; i < n; i++) {
            matrixB[i] = matrixListB.get(i);
        }

        //Вычисление решения системы

        GaussSystem result = MatrixOperations.gaussElimination(matrixA, matrixB);

        PrintWriter printer = new PrintWriter(new File(fileRes));
        if (result.isSingular()){
            printer.println("Матрица вырождена.");
        } else{

            //Вычисление невязок

            double[] nevyazka = new double[n];
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += matrixA[i][j] * result.getSystem()[i];
                }
                nevyazka[i] = matrixB[i] - sum;
            }

            //Вычисление обратной матрицы

            double det = result.getDeterminant();
            double[][] inverse = MatrixOperations.invertMatrix(matrixACopy);

            //Вывод результатов

            printer.println("Получившиеся матрицы:");
            int i = 0;
            for (double[] row : matrixA) {
                for (double num : row) {
                    printer.print(num + " ");
                }
                printer.print("| ");
                printer.print(matrixB[i]);
                printer.println();
                ++i;
            }

            printer.println();

            printer.println("Решение системы:");
            for (int k = 0; k < n; ++k){
                printer.printf("X%d = %.16f\n", k + 1, result.getSystem()[k]);
            }
            printer.println();

            printer.print("Определитель матрицы равен: ");
            printer.println(result.getDeterminant());
            printer.println();

            printer.println("Невязки:");
            for (int k = 0; k < n; k++) {
                printer.printf("r%d = %.6e%n", k + 1, nevyazka[k]);
            }
            printer.println();

            printer.println("Обратная матрица");
            for (int k = 0; k < n; ++k){
                for (int j = 0; j < n; ++j){
                    printer.printf("%.16f ", inverse[k][j]);
                }
                printer.println();
            }
        }
        printer.close();
    }
}