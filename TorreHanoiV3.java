// Programa desarrollado por Jose Alejandro Briones Arroyo el 24 de septiembre de 2023.
import javax.swing.JOptionPane;

public class TorreHanoiV3 {
    // Función principal para resolver las Torres de Hanoi
    public static void MovimientoTorres(int numeroDiscos, int[][] torres, int torre1, int torre2, int torre3) {
        if (numeroDiscos == 1) {
            moverDisco(torres, torre1, torre3); // Mueve el disco de torre1 a torre3
            imprimirTorres(torres); // Imprime el estado de las torres después del movimiento
        } else {
            MovimientoTorres(numeroDiscos - 1, torres, torre1, torre3, torre2);
            moverDisco(torres, torre1, torre3);
            imprimirTorres(torres);
            MovimientoTorres(numeroDiscos - 1, torres, torre2, torre1, torre3);
        }
    }

    public static void moverDisco(int[][] torres, int torre1, int torre3) {
        for (int i = 0; i < torres.length; i++) {
            if (torres[i][torre1] != 0) {
                int disco = torres[i][torre1];
                torres[i][torre1] = 0;

                for (int j = torres.length - 1; j >= 0; j--) {
                    if (torres[j][torre3] == 0) {
                        torres[j][torre3] = disco; // Mueve el disco a la torre torre3
                        break;
                    }
                }
                break;
            }
        }
        
        // Ordenar la torre de torre3 de manera ascendente con el pivote en la última posición
        quickSort(torres, 0, torres.length - 1, torre3);
    }

    public static void imprimirTorres(int[][] torres) {
        System.out.println("TORRE: 1");
        for (int i = 0; i < torres.length; i++) {
            System.out.println(torres[i][0]);
        }

        System.out.println("TORRE: 2");
        for (int i = 0; i < torres.length; i++) {
            System.out.println(torres[i][1]);
        }

        System.out.println("TORRE: 3");
        for (int i = 0; i < torres.length; i++) {
            System.out.println(torres[i][2]);
        }

        System.out.println("----------");
    }

    // Función para realizar el ordenamiento Quicksort
    public static void quickSort(int[][] arreglo, int inicio, int fin, int k) {
        if (inicio < fin) {
            // Llama a la función particionar para dividir el arreglo
            int indiceParticion = particionar(arreglo, inicio, fin, k);

            // Llama recursivamente a quickSort en las sublistas izquierda y derecha
            quickSort(arreglo, inicio, indiceParticion - 1, k);
            quickSort(arreglo, indiceParticion + 1, fin, k);
        }
    }

    // Función para realizar el particionado en Quicksort
    public static int particionar(int[][] arreglo, int inicio, int fin, int k) {
        int pivote = arreglo[fin][k];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (arreglo[j][k] <= pivote) {
                i++;

                // Intercambia arreglo[i] y arreglo[j]
                int temp = arreglo[i][k];
                arreglo[i][k] = arreglo[j][k];
                arreglo[j][k] = temp;
            }
        }

        // Intercambia arreglo[i+1] y el pivote
        int temp = arreglo[i + 1][k];
        arreglo[i + 1][k] = arreglo[fin][k];
        arreglo[fin][k] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int numeroDiscos = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de discos:"));
        int[][] torres = new int[numeroDiscos][3];

        for (int i = 0; i < numeroDiscos; i++) {
            torres[i][0] = i+1;
            torres[i][1] = 0;
            torres[i][2] = 0;
        }

        imprimirTorres(torres);
        MovimientoTorres(numeroDiscos, torres, 0, 1, 2);
    }
}
