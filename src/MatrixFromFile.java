import java.io.*;
import java.util.*;

public class MatrixFromFile {
    public static void readMatrixFromFile(List<int[]> matrixList, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Разбиваем по пробелам
                int[] row = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    row[i] = Integer.parseInt(parts[i]); // Преобразуем в числа
                }

                matrixList.add(row);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
