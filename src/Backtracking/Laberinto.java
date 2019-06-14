package Backtracking;

import java.util.Scanner;

public class Laberinto {

    private static int contadorPasos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int dimension = sc.nextInt();
        int [][] laberinto = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                laberinto[i][j] = sc.nextInt();
            }
        }

        contadorPasos = dimension * dimension;
        resolverLaberinto(laberinto, dimension);
    }

    private static void resolverLaberinto(int[][] laberinto, int dimension) {
        int[][] recorrido = new int[dimension][dimension]; // Por defecto todas las casillas se inicializan a 0
        int nMovimientos = 1;

        if (labRecursivo(laberinto, 0, 0, recorrido, dimension, nMovimientos) == false) {
            if (contadorPasos == dimension * dimension) {
                System.out.println("SIN SOLUCION");
            } else {
                System.out.println(contadorPasos);
            }
        }
    }

    private static boolean labRecursivo(int[][] laberinto, int x, int y, int[][] recorrido, int dimension, int nMovimientos) {

        if ((x == dimension - 1) && (y == dimension - 1)) { // Estamos en la salida
            recorrido[x][y] = 1;
            if (nMovimientos < contadorPasos) {
                contadorPasos = nMovimientos; //Se ha encontrado un recorrido mas corto
            }
        }

        // Si no estamos en la salida comprobamos si podemos marcar en nuestro recorrido la casilla en la que estamos
        if (sePuedeMover(laberinto, x, y, dimension)) {
            if (sePuedeMover(recorrido, x, y, dimension)) {
                recorrido[x][y] = 1;

                // Intentamos avanzar en alguna direccion
                if (labRecursivo(laberinto, x + 1, y, recorrido, dimension, nMovimientos + 1))
                    return true;

                if (labRecursivo(laberinto, x - 1, y, recorrido, dimension, nMovimientos + 1))
                    return true;

                if (labRecursivo(laberinto, x, y + 1, recorrido, dimension, nMovimientos + 1))
                    return true;

                if (labRecursivo(laberinto, x, y - 1, recorrido, dimension, nMovimientos + 1))
                    return true;

                // Si el programa llega hasta aqui significa que no puede avanzar en ninguna direccion
                // por lo tanto VUELVE HACIA ATRAS
                recorrido[x][y] = 0;
                return false;
            }
        }

        return false;
    }

    private static boolean sePuedeMover(int[][] laberinto, int x, int y, int dimension) {
        return ((x >= 0) && (x < dimension) && (y >= 0) && (y < dimension) && (laberinto[x][y] == 0));
    }

}
