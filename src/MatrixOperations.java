public class MatrixOperations {

    //Решение СЛАУ

    public static GaussSystem gaussElimination(double[][] matrixA, double[] matrixB){

        double det = 1;
        int swapCount = 0;
        int n = matrixA.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            //Поиск максимального элемента в столбце i

            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrixA[k][i]) > Math.abs(matrixA[maxRow][i])) {
                    maxRow = k;
                }
            }

            //Если максимальный элемент близок к нулю, матрица вырождена

            if (Math.abs(matrixA[maxRow][i]) < 1e-12) {
                return new GaussSystem(null, 0, true);
            }

            //Обмен строк если необходимо

            if (maxRow != i) {
                double[] tempRow = matrixA[i];
                matrixA[i] = matrixA[maxRow];
                matrixA[maxRow] = tempRow;
                double tempVal = matrixB[i];
                matrixB[i] = matrixB[maxRow];
                matrixB[maxRow] = tempVal;
                swapCount++;
            }

            //Умножаем определитель на очередной элемент главной диагонали

            det *= matrixA[i][i];

            //Обнуление элементов ниже главной диагонали

            for (int j = i + 1; j < n; j++) {
                double factor = matrixA[j][i] / matrixA[i][i];
                for (int k = i; k < n; k++) {
                    matrixA[j][k] -= factor * matrixA[i][k];
                }
                matrixB[j] -= factor * matrixB[i];
            }
        }

        //Если меняли строки местами, то меняем знак определителя

        if (swapCount % 2 != 0) {
            det = -det;
        }

        //Обратный ход (решение системы)

        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrixA[i][j] * result[j];
            }
            result[i] = (matrixB[i] - sum) / matrixA[i][i];
        }

        return new GaussSystem(result, det, false);
    }

    //Вычисление обратной матрицы

    public static double[][] invertMatrix(double[][] matrix) throws Exception {
        int n = matrix.length;

        //Формирование расширенной матрицы [A | E]

        double[][] extendedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                extendedMatrix[i][j] = matrix[i][j];
            }
            for (int j = n; j < 2 * n; j++) {
                extendedMatrix[i][j] = (i == j - n) ? 1 : 0;
            }
        }

        //Прямой ход с выбором главного элемента

        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(extendedMatrix[k][i]) > Math.abs(extendedMatrix[maxRow][i])) {
                    maxRow = k;
                }
            }
            if (Math.abs(extendedMatrix[maxRow][i]) < 1e-12) {
                throw new Exception("Матрица вырождена, обратная матрица не существует.");
            }

            //Обмен строк

            double[] temp = extendedMatrix[i];
            extendedMatrix[i] = extendedMatrix[maxRow];
            extendedMatrix[maxRow] = temp;

            //Нормализация опорной строки

            double pivot = extendedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                extendedMatrix[i][j] /= pivot;
            }

            //Обнуление элементов в столбце i для всех остальных строк

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = extendedMatrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        extendedMatrix[k][j] -= factor * extendedMatrix[i][j];
                    }
                }
            }
        }

        //Извлечение обратной матрицы из расширенной матрицы

        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = extendedMatrix[i][j + n];
            }
        }
        return inverse;
    }

}
