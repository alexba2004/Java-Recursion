import java.util.Arrays;

public class CuadroMagico {

    // Función para encontrar la posición del 0 en la matriz
    private static int[] findZeroPosition(int[][] matrix) {
        // Itera a través de la matriz para encontrar la posición del valor 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    return new int[]{i, j}; // Retorna la posición (i, j) del valor 0
                }
            }
        }
        return null; // Si no se encuentra el valor 0, retorna null
    }

    // Función para verificar si la matriz está ordenada
    private static boolean isSorted(int[][] matrix) {
        // Convierte la matriz en un arreglo unidimensional y lo ordena
        int[] flattened = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
        int[] sorted = Arrays.copyOf(flattened, flattened.length);
        Arrays.sort(sorted);
        // Compara el arreglo original con el arreglo ordenado para verificar si está ordenada
        return Arrays.equals(flattened, sorted);
    }

    // Función para imprimir la matriz
    private static void printMatrix(int[][] matrix) {
        // Recorre la matriz y crea una representación en forma de cadena para imprimir
        String result = "";
        for (int[] row : matrix) {
            for (int value : row) {
                result += "[" + value + "]";
            }
            result += "\n";
        }
        System.out.println(result);
    }

    // Función para intercambiar dos elementos en la matriz
    private static void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        // Realiza un intercambio de dos elementos en la matriz
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }

    // Función para resolver el puzzle de manera recursiva
    public static void solvePuzzleRecursive(int[][] matrix, int lastMovedValue) {
        int[] zeroPosition = findZeroPosition(matrix); // Encuentra la posición del valor 0
        int i = zeroPosition[0];
        int j = zeroPosition[1];

        // Verifica el caso base: la matriz está ordenada o el valor 0 está en la última posición
        if (isSorted(matrix) || (i == matrix.length - 1 && j == matrix[i].length - 1)) {
            return; // Retorna y finaliza la recursión
        }

        int maxNeighbor = Integer.MIN_VALUE;
        int maxI = -1;
        int maxJ = -1;

        // Compara con arriba
        if (i > 0 && matrix[i - 1][j] > maxNeighbor && matrix[i - 1][j] != lastMovedValue) {
            maxNeighbor = matrix[i - 1][j];
            maxI = i - 1;
            maxJ = j;
        }
        // Compara con abajo
        if (i < matrix.length - 1 && matrix[i + 1][j] > maxNeighbor && matrix[i + 1][j] != 11 && matrix[i + 1][j] != 15 && matrix[i + 1][j] != lastMovedValue) {
            maxNeighbor = matrix[i + 1][j];
            maxI = i + 1;
            maxJ = j;
        }
        // Compara con izquierda
        if (j > 0 && matrix[i][j - 1] > maxNeighbor && matrix[i][j - 1] != lastMovedValue) {
            maxNeighbor = matrix[i][j - 1];
            maxI = i;
            maxJ = j - 1;
        }
        // Compara con derecha
        if (j < matrix[i].length - 1 && matrix[i][j + 1] > maxNeighbor && matrix[i][j + 1] != lastMovedValue) {
            maxI = i;
            maxJ = j + 1;
        }

        lastMovedValue = maxNeighbor; // Actualiza el último valor movido

        if (maxI != -1 && maxJ != -1) {
            swap(matrix, i, j, maxI, maxJ); // Realiza el intercambio
            printMatrix(matrix); // Imprime la matriz después del intercambio
            solvePuzzleRecursive(matrix, lastMovedValue); // Llamada recursiva con el nuevo valor movido
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 8, 0},
            {9, 10, 7, 11},
            {13, 14, 15, 12}
        };
        System.out.println("<-----Estado inicial:----->\n");
        printMatrix(matrix); // Imprime la matriz inicial
        System.out.println("<-----Movimientos:----->\n");
        solvePuzzleRecursive(matrix, 0); // Llama a la función recursiva con el valor 0 como el último valor movido
        System.out.println("<-----Puzzle resuelto:----->\n");
        printMatrix(matrix); // Imprime la matriz resuelta
    }
}
