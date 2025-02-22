import java.io.*;
import java.util.*;

public class MatrixFromFile {
    public static void readMatrixFromFile(List<double[]> matrixListA, String filenameA, List<Double> matrixListB, String filenameB) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filenameA))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Разбиваем по пробелам
                double[] row = new double[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    row[i] = Double.parseDouble(parts[i]); // Преобразуем в числа
                }

                matrixListA.add(row);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }



        try (BufferedReader reader = new BufferedReader(new FileReader(filenameB))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Разбиваем по пробелам

                for (String part : parts) {
                    if (!part.isEmpty()) {
                        matrixListB.add(Double.parseDouble(part));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }
}
