// Programa desarrollado por Jose Alejandro Briones el 06 de octubre del 2023.
import javax.swing.JOptionPane;

public class CalculadoraMatricesRecursivaV4 {
    public static void main(String[] args) {
        // Se inicia un bucle infinito para permitir múltiples cálculos.
        while (true) {
            int filasA, columnasA, filasB, columnasB, filasC, columnasC;

            // Se solicitan las dimensiones de las matrices A, B y C al usuario.
            filasA = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas de la matriz A:"));
            columnasA = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas de la matriz A:"));
            filasB = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas de la matriz B:"));
            columnasB = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas de la matriz B:"));
            filasC = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas de la matriz C:"));
            columnasC = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas de la matriz C:"));

            // Se verifica si las dimensiones de las matrices son iguales.
            if (filasA != filasB || filasB != filasC || columnasA != columnasB || columnasB != columnasC) {
                // Si las dimensiones no son iguales, se muestra un mensaje de error y se da la opción de ingresar nuevos tamaños.
                JOptionPane.showMessageDialog(null, "Las dimensiones de las matrices no son iguales.", "Error", JOptionPane.ERROR_MESSAGE);
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea insertar nuevos tamaños para las matrices?", "Nuevos Tamaños", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    continue; // Se permite al usuario ingresar nuevos tamaños.
                } else {
                    break; // Se sale del programa si el usuario no desea ingresar nuevos tamaños.
                }
            } else {
                // Si las dimensiones son iguales, se procede a llenar las matrices.
                double[][] matrizA = llenarMatriz("A", filasA, columnasA);
                double[][] matrizB = llenarMatriz("B", filasB, columnasB);
                double[][] matrizC = llenarMatriz("C", filasC, columnasC);

                // Se realiza la operación entre las matrices ingresadas.
                realizarOperaciones(matrizA, matrizB, matrizC);
            }
        }
    }

    // Función que permite al usuario realizar operaciones entre matrices.
    public static void realizarOperaciones(double[][] matrizA, double[][] matrizB, double[][] matrizC) {
        String operacion = JOptionPane.showInputDialog("Ingrese la operación entre matrices (ejemplo: A-B*C):");

        if (operacion == null) {
            // Si el usuario cancela la operación, se pregunta si desea ingresar nuevas matrices o salir del programa.
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea ingresar nuevas matrices?", "Nuevas Matrices", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                return; // Se permite al usuario ingresar nuevas matrices.
            } else {
                System.exit(0); // Se sale del programa.
            }
        }

        // Se realiza la operación ingresada y se muestra el resultado.
        double[][] resultado = operarMatrices(matrizA, matrizB, matrizC, operacion);

        if (resultado != null) {
            mostrarMatriz(resultado, "Resultado");
        }

        // Se permite al usuario realizar más operaciones con las mismas matrices.
        realizarOperaciones(matrizA, matrizB, matrizC);
    }

    // Función que realiza la operación entre matrices.
    public static double[][] operarMatrices(double[][] matrizA, double[][] matrizB, double[][] matrizC, String operacion) {
        operacion = operacion.replaceAll(" ", "").toUpperCase();
        double[][] resultado = evaluarOperacion(matrizA, matrizB, matrizC, operacion);

        if (resultado == null) {
            JOptionPane.showMessageDialog(null, "Operación no válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return resultado;
    }

    // Función que evalúa la operación ingresada.
    public static double[][] evaluarOperacion(double[][] matrizA, double[][] matrizB, double[][] matrizC, String operacion) {
        String[] partes = operacion.split("[+\\-]");
        double[][] resultado = null;

        for (String parte : partes) {
            double[][] operando = evaluarMultiplicacionDivision(matrizA, matrizB, matrizC, parte);
            if (operando == null) {
                return null;
            }

            if (resultado == null) {
                resultado = operando;
            } else {
                String operador = operacion.substring(operacion.indexOf(parte) - 1, operacion.indexOf(parte));
                resultado = aplicarOperador(resultado, operando, operador);
            }
        }

        return resultado;
    }

    // Función que evalúa la multiplicación y división en una operación.
    public static double[][] evaluarMultiplicacionDivision(double[][] matrizA, double[][] matrizB, double[][] matrizC, String operacion) {
        String[] partes = operacion.split("[*/]");
        double[][] resultado = null;

        for (String parte : partes) {
            double[][] operando = evaluarTermino(matrizA, matrizB, matrizC, parte);
            if (operando == null) {
                return null;
            }

            if (resultado == null) {
                resultado = operando;
            } else {
                String operador = operacion.substring(operacion.indexOf(parte) - 1, operacion.indexOf(parte));
                resultado = aplicarOperador(resultado, operando, operador);
            }
        }

        return resultado;
    }

    // Función que evalúa un término en una operación.
    public static double[][] evaluarTermino(double[][] matrizA, double[][] matrizB, double[][] matrizC, String termino) {
        termino = termino.replaceAll(" ", "").toUpperCase();

        if (esNumero(termino)) {
            return multiplicarPorEscalar(Double.parseDouble(termino), matrizA.length, matrizA[0].length);
        }

        switch (termino) {
            case "A":
                return matrizA;
            case "B":
                return matrizB;
            case "C":
                return matrizC;
            default:
                return null;
        }
    }

    // Función que verifica si una cadena es un número.
    public static boolean esNumero(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Función que multiplica una matriz por un escalar.
    public static double[][] multiplicarPorEscalar(double escalar, int filas, int columnas) {
        double[][] resultado = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = escalar;
            }
        }
        return resultado;
    }

    // Función que aplica un operador (+, -, *, /) entre dos matrices.
    public static double[][] aplicarOperador(double[][] operando1, double[][] operando2, String operador) {
        int filas = operando1.length;
        int columnas = operando1[0].length;
        double[][] resultado = new double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                double valor1 = operando1[i][j];
                double valor2 = operando2[i][j];
                double resultadoElemento = 0;

                switch (operador) {
                    case "+":
                        resultadoElemento = valor1 + valor2;
                        break;
                    case "-":
                        resultadoElemento = valor1 - valor2;
                        break;
                    case "*":
                        resultadoElemento = valor1 * valor2;
                        break;
                    case "/":
                        if (valor2 == 0) {
                            JOptionPane.showMessageDialog(null, "División por cero encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                            return null;
                        }
                        resultadoElemento = valor1 / valor2;
                        break;
                }

                resultado[i][j] = resultadoElemento;
            }
        }

        return resultado;
    }

    // Función que muestra una matriz en un cuadro de diálogo.
    public static void mostrarMatriz(double[][] matriz, String titulo) {
        StringBuilder resultadoStr = new StringBuilder();
        for (double[] fila : matriz) {
            for (double valor : fila) {
                resultadoStr.append("[").append(valor).append("] ");
            }
            resultadoStr.append("\n");
        }
        JOptionPane.showMessageDialog(null, titulo + ":\n" + resultadoStr.toString(), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // Función que crea y llena una matriz con valores ingresados por el usuario.
    public static double[][] llenarMatriz(String nombreMatriz, int filas, int columnas) {
        double[][] matriz = new double[filas][columnas];
        return llenarMatrizRecursivo(matriz, nombreMatriz, 0, 0);
    }

    // Función recursiva que llena una matriz con valores ingresados por el usuario.
    public static double[][] llenarMatrizRecursivo(double[][] matriz, String nombreMatriz, int fila, int columna) {
        if (fila == matriz.length) {
            return matriz;
        }

        if (columna == matriz[0].length) {
            return llenarMatrizRecursivo(matriz, nombreMatriz, fila + 1, 0);
        }

        String valorStr = JOptionPane.showInputDialog("Ingrese el valor en la posición (" + fila + ", " + columna + ") de la matriz " + nombreMatriz + ":");
        matriz[fila][columna] = Double.parseDouble(valorStr);

        return llenarMatrizRecursivo(matriz, nombreMatriz, fila, columna + 1);
    }
}
