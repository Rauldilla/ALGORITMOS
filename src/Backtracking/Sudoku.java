package Backtracking;

import java.util.Scanner;

public class Sudoku {

    private static final int DIMENSION = 9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sudoku = new int[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sudoku[i][j] = sc.nextInt();
            }
        }

        solucionarSudoku(sudoku);
        mostrarSudoku(sudoku);
    }

    private static void mostrarSudoku(int[][] sudoku) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean solucionarSudoku(int[][] sudoku) {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {

                // Buscamos un espacio vacio
                if (sudoku[fila][columna] == 0) {
                    // Comenzamos a comprobar posibles numeros para ese espacio
                    for (int numero = 1; numero <= DIMENSION; numero++) {
                        if (esNumeroPermitido(sudoku, numero, fila, columna)) {

                            sudoku[fila][columna] = numero;

                            // Comienza el BACKTRACKING
                            if (solucionarSudoku(sudoku)) {
                                return true;
                            } else {
                                sudoku[fila][columna] = 0;
                            }
                        }
                    }
                    // Si se llega a este return significa que no nos sirve ningun numero para ese espacio, por lo tanto hay que cambiar alguno de los anteriores
                    return false;
                }
            }
        }
        return true; // Sudoku resuleto
    }

    private static boolean esNumeroPermitido(int[][] sudoku, int numero, int fila, int columna) {
        return !(comprobarFila(sudoku, numero, fila) || comprobarColumna(sudoku, numero, columna) || comprobarCuadricula(sudoku, numero, fila, columna));
    }

    private static boolean comprobarFila(int[][] sudoku, int numero, int fila) {
        for (int i = 0; i < DIMENSION; i++) {
            if (sudoku[fila][i] == numero) {
                return true;
            }
        }
        return false;
    }

    private static boolean comprobarColumna(int[][] sudoku, int numero, int columna) {
        for (int i = 0; i < DIMENSION; i++) {
            if (sudoku[i][columna] == numero) {
                return true;
            }
        }
        return false;
    }

    private static boolean comprobarCuadricula(int[][] sudoku, int numero, int fila, int columna) {
        int f = fila - (fila % 3);
        int c = columna - (columna % 3);

        for (int i = f; i < f+3; i++) {
            for (int j = c; j < c+3; j++) {
                if (sudoku[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }
}
