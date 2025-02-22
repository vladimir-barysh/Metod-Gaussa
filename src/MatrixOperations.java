import java.util.List;

public class MatrixOperations {

    public static double[][] matrixSum(double[][] matrixA, double[][] matrixB){

        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            throw new IllegalArgumentException("Матрицы должны быть одного размера!");
        }

        int rows = matrixA.length;
        int cols = matrixA[0].length;
        double[][] result = new double[rows][cols];

        // Сложение соответствующих элементов
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    public static double[] gaussElimination(double[][] matrixA, double[] matrixB){

        double det = 1;
        int swapCount = 0;
        int n = matrixA.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            // Поиск максимального элемента в столбце i
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrixA[k][i]) > Math.abs(matrixA[maxRow][i])) {
                    maxRow = k;
                }
            }
            if (Math.abs(matrixA[maxRow][i]) < 1e-12) {
                return null;
            }
            // Обмен строк если необходимо
            if (maxRow != i) {
                double[] tempRow = matrixA[i];
                matrixA[i] = matrixA[maxRow];
                matrixA[maxRow] = tempRow;
                double tempVal = matrixB[i];
                matrixB[i] = matrixB[maxRow];
                matrixB[maxRow] = tempVal;
                swapCount++;
            }

            // Умножаем определитель на опорный элемент
            det *= matrixA[i][i];

            // Обнуление элементов ниже главной диагонали
            for (int j = i + 1; j < n; j++) {
                double factor = matrixA[j][i] / matrixA[i][i];
                for (int k = i; k < n; k++) {
                    matrixA[j][k] -= factor * matrixA[i][k];
                }
                matrixB[j] -= factor * matrixB[i];
            }
        }

        if (swapCount % 2 != 0) {
            det = -det;
        }

        // Обратный ход (решение системы)
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrixA[i][j] * result[j];
            }
            result[i] = (matrixB[i] - sum) / matrixA[i][i];
        }
        return result;
    }
}
