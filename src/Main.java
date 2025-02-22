import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        //чтение матриц из файлов
        String fileA = "matrixA.txt";
        String fileB = "matrixB.txt";
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

        //вычисление
        GaussSystem result = MatrixOperations.gaussElimination(matrixA, matrixB);

        // Вычисление невязок: r = A_orig*x - b_orig
        double[] nevyazka = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrixA[i][j] * result.getSystem()[i];
            }
            nevyazka[i] = sum - matrixB[i];
        }

        // Вычисление обратной матрицы
        double det = result.getDeterminant();
        double[][] inverse = MatrixOperations.invertMatrix(matrixACopy);


        // Вывод результатов
        if (result.isSingular()){
            System.out.println("Матрица вырождена.");
        } else{
            System.out.println("Получившиеся матрицы:");
            int i = 0;
            for (double[] row : matrixA) {
                for (double num : row) {
                    System.out.print(num + " ");
                }
                System.out.print("| ");
                System.out.print(matrixB[i]);
                System.out.println();
                ++i;
            }

            System.out.println();

            System.out.println("Решение системы:");
            for (int k = 0; k < n; ++k){
                System.out.printf("X%d = %.16f\n", k + 1, result.getSystem()[k]);
            }

            System.out.print("Определитель матрицы равен: ");
            System.out.println(result.getDeterminant());

            System.out.println("Невязки:");
            for (int k = 0; k < n; k++) {
                System.out.printf("r%d = %.6e%n", k + 1, nevyazka[k]);
            }
            System.out.println();

            System.out.println("Обратная матрица");
            for (int k = 0; k < n; ++k){
                for (int j = 0; j < n; ++j){
                    System.out.printf("%.16f ", inverse[k][j]);
                }
                System.out.println();
            }
        }
    }
}