// Programa desarrollado por Jose Alejandro Briones Arroyo el 16 de septiembre de 2023.
import javax.swing.JOptionPane;

public class ConversorNumerico {
    // Función para la conversión de números decimales a binarios de forma recursiva.
    public static String decimalABinario(int decimal) {
        if (decimal == 0) {
            return "0"; // Caso base: Si el número decimal es cero, se retorna "0".
        }
        if (decimal == 1) {
            return "1"; // Caso base 2: Si el número decimal es uno, se retorna "1".
        }
        int residuo = decimal % 2;
        // Se realiza una llamada recursiva para los dígitos restantes y se agrega el residuo.
        return quitarCaracteresBasura(decimalABinario(decimal / 2)) + residuo;
    }

    // Función para la conversión de números binarios a decimales de forma recursiva.
    public static int binarioADecimal(String binario) {
        if (binario.isEmpty()) {
            return 0; // Caso base: Si la cadena binaria está vacía, se retorna 0.
        }
        int ultimoDigito = Character.getNumericValue(binario.charAt(binario.length() - 1));
        // Se realiza una llamada recursiva para los dígitos restantes y se suma el último dígito ponderado.
        return ultimoDigito + 2 * binarioADecimal(binario.substring(0, binario.length() - 1));
    }

    // Función para la conversión de números decimales a octales de forma recursiva.
    public static String decimalAOctal(int decimal) {
        if (decimal == 0) {
            return "0"; // Caso base: Si el número decimal es cero, se retorna "0".
        }
        int residuo = decimal % 8;
        // Se realiza una llamada recursiva para los dígitos restantes y se agrega el residuo.
        return quitarCaracteresBasura(decimalAOctal(decimal / 8)) + residuo;
    }

    // Función para la conversión de números octales a decimales de forma recursiva.
    public static int octalADecimal(String octal) {
        if (octal.isEmpty()) {
            return 0; // Caso base: Si la cadena octal está vacía, se retorna 0.
        }
        int ultimoDigito = Character.getNumericValue(octal.charAt(octal.length() - 1));
        // Se realiza una llamada recursiva para los dígitos restantes y se suma el último dígito ponderado.
        return ultimoDigito + 8 * octalADecimal(octal.substring(0, octal.length() - 1));
    }

    // Función para la conversión de números decimales a hexadecimales de forma recursiva.
    public static String decimalAHexadecimal(int decimal) {
        if (decimal == 0) {
            return "0"; // Caso base: Si el número decimal es cero, se retorna "0".
        }
        int residuo = decimal % 16;
        char digitoHexadecimal = (residuo < 10) ? (char) (residuo + '0') : (char) (residuo - 10 + 'A');
        // Se realiza una llamada recursiva para los dígitos restantes y se agrega el dígito hexadecimal.
        return quitarCaracteresBasura(decimalAHexadecimal(decimal / 16)) + digitoHexadecimal;
    }

    // Función para la conversión de números hexadecimales a decimales de forma recursiva.
    public static int hexadecimalADecimal(String hexadecimal) {
        if (hexadecimal.isEmpty()) {
            return 0; // Caso base: Si la cadena hexadecimal está vacía, se retorna 0.
        }
        char ultimoCaracter = hexadecimal.charAt(hexadecimal.length() - 1);
        int digito = Character.isDigit(ultimoCaracter) ? Character.getNumericValue(ultimoCaracter) : (ultimoCaracter - 'A' + 10);
        // Se realiza una llamada recursiva para los dígitos restantes y se suma el dígito ponderado.
        return digito + 16 * hexadecimalADecimal(hexadecimal.substring(0, hexadecimal.length() - 1));
    }

    // Función para la conversión de números decimales a Zn de forma recursiva.
    public static String decimalAZn(int valorABuscar, int z) {
        if (valorABuscar == 0) {
            return "0"; // Caso base: Cuando el número valorABuscar es 0 en Zn, el resultado es "0".
        } else if (z <= 1) {
            return "0"; // Caso base 2: Cuando Z es menor o igual a 1, el resultado siempre será "0".
        } else if (valorABuscar < z) {
            valorABuscar = valorABuscar - 1;
            String zn = Integer.toString(valorABuscar);
            return zn;
        }
        int residuo = valorABuscar % z;

        // Se obtiene el dígito Zn restado y se agrega al resultado utilizando recursividad.
        String resultadoParcial = decimalAZn(valorABuscar / z, z) + residuo;

        // Se utiliza la función quitarCaracteresBasura para eliminar los ceros no significativos al principio del resultado.
        return quitarCaracteresBasura(resultadoParcial);
    }

    public static void main(String[] args) {
        boolean realizarOtraConversion = true;

        while (realizarOtraConversion) {
            String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:\n" +
                            "1. Decimal a Binario\n" +
                            "2. Binario a Decimal\n" +
                            "3. Decimal a Octal\n" +
                            "4. Octal a Decimal\n" +
                            "5. Decimal a Hexadecimal\n" +
                            "6. Hexadecimal a Decimal\n" +
                            "7. Decimal a Zn"
            );

            switch (opcion) {
                case "1": // Decimal a Binario
                    String entradaDecimal = JOptionPane.showInputDialog("Ingrese un número decimal:");
                    int decimal = Integer.parseInt(entradaDecimal);
                    String binario = decimalABinario(decimal);
                    JOptionPane.showMessageDialog(null, "El número decimal " + decimal + " convertido a binario es: " + binario);
                    break;

                case "2": // Binario a Decimal
                    String entradaBinario = JOptionPane.showInputDialog("Ingrese un número binario:");
                    int decimalDesdeBinario = binarioADecimal(entradaBinario);
                    JOptionPane.showMessageDialog(null, "El número binario " + entradaBinario + " convertido a decimal es: " + decimalDesdeBinario);
                    break;

                case "3": // Decimal a Octal
                    String entradaDecimalOctal = JOptionPane.showInputDialog("Ingrese un número decimal:");
                    int decimalOctal = Integer.parseInt(entradaDecimalOctal);
                    String octal = decimalAOctal(decimalOctal);
                    JOptionPane.showMessageDialog(null, "El número decimal " + decimalOctal + " convertido a octal es: " + octal);
                    break;

                case "4": // Octal a Decimal
                    String entradaOctal = JOptionPane.showInputDialog("Ingrese un número octal:");
                    int decimalDesdeOctal = octalADecimal(entradaOctal);
                    JOptionPane.showMessageDialog(null, "El número octal " + entradaOctal + " convertido a decimal es: " + decimalDesdeOctal);
                    break;

                case "5": // Decimal a Hexadecimal
                    String entradaDecimalHexadecimal = JOptionPane.showInputDialog("Ingrese un número decimal:");
                    int decimalHexadecimal = Integer.parseInt(entradaDecimalHexadecimal);
                    String hexadecimal = decimalAHexadecimal(decimalHexadecimal);
                    JOptionPane.showMessageDialog(null, "El número decimal " + decimalHexadecimal + " convertido a hexadecimal es: " + hexadecimal);
                    break;

                case "6": // Hexadecimal a Decimal
                    String entradaHexadecimal = JOptionPane.showInputDialog("Ingrese un número hexadecimal:");
                    int decimalDesdeHexadecimal = hexadecimalADecimal(entradaHexadecimal);
                    JOptionPane.showMessageDialog(null, "El número hexadecimal " + entradaHexadecimal + " convertido a decimal es: " + decimalDesdeHexadecimal);
                    break;

                case "7": // Zn
                    String entradaDecimalZn = JOptionPane.showInputDialog("Ingrese un número decimal:");
                    int decimalZn = Integer.parseInt(entradaDecimalZn);
                    int z = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el valor de Z:"));
                    String zn = decimalAZn(decimalZn, z);
                    JOptionPane.showMessageDialog(null, "El número decimal " + decimalZn + " convertido a ZN: " + z + " es: " + zn);
                    break;

                default: // Default
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }

            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea realizar otra conversión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (respuesta != JOptionPane.YES_OPTION) {
                realizarOtraConversion = false;
            }
        }
    }

    // Función para eliminar caracteres no deseados al principio de la cadena.
    public static String quitarCaracteresBasura(String cadena) {
        int indice = 0;
        while (indice < cadena.length() - 1 && cadena.charAt(indice) == '0') {
            indice++;
        }
        return cadena.substring(indice);
    }
}