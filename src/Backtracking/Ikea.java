import java.awt.*;
import java.util.Scanner;

public class Ikea {

    private static final int ENTRADA = 0;
    private static final int SALIDA = 1;
    private static final int SEPARACION = 2;

    private static int contadorMovimientos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nFilas = sc.nextInt();
        int nColumnas = sc.nextInt();
        int nSecciones = sc.nextInt();

        int[][] plano = new int[nFilas][nColumnas];
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                plano[i][j] = sc.nextInt();
            }
        }

        Point entrada = buscarPunto(plano, ENTRADA);

        contadorMovimientos = nFilas * nColumnas;
        buscarRuta(plano, entrada, nFilas, nColumnas, nSecciones);
    }

    private static void buscarRuta(int[][] plano, Point entrada, int nFilas, int nColumnas, int nSecciones) {
        int[][] ruta = new int[nFilas][nColumnas];
        boolean[] seccionesVisitadas = inicializarSecciones(nSecciones);
        int nMovimientos = 1;

        if (buscarRutaRecursivo(plano, entrada.x, entrada.y, ruta, seccionesVisitadas, nMovimientos) == false) {
            System.out.println(contadorMovimientos);
        }
    }

    private static boolean buscarRutaRecursivo(int[][] plano, int x, int y, int[][] ruta, boolean[] seccionesVisitadas, int nMovimientos) {
        if (sePuedeMover(plano, x, y)) {
            if (esSalida(plano, x, y) && seccionesRecorridas(seccionesVisitadas)) {
                ruta[x][y] = 1;
                if (nMovimientos < contadorMovimientos) {
                    contadorMovimientos = nMovimientos;
                }
            }

            if (!hemosPasado(ruta, x, y)) {
                ruta[x][y] = 1;
                boolean marcadoAhora = false;
                if (!hemosVisitadoSeccion(plano, seccionesVisitadas, x, y)) {
                    marcarSeccion(seccionesVisitadas, plano, x, y);
                    marcadoAhora = true;
                }

                if (buscarRutaRecursivo(plano, x + 1, y, ruta, seccionesVisitadas, nMovimientos + 1))
                    return true;

                if (buscarRutaRecursivo(plano, x - 1, y, ruta, seccionesVisitadas, nMovimientos + 1))
                    return true;

                if (buscarRutaRecursivo(plano, x, y + 1, ruta, seccionesVisitadas, nMovimientos + 1))
                    return true;

                if (buscarRutaRecursivo(plano, x, y - 1, ruta, seccionesVisitadas, nMovimientos + 1))
                    return true;

                ruta[x][y] = 0;
                if (marcadoAhora) {
                    desmarcarSeccion(seccionesVisitadas, plano, x, y);
                }
                return false;
            }
        }

        return false;
    }

    private static boolean hemosVisitadoSeccion(int[][] plano, boolean[] seccionesVisitadas, int x, int y) {
        int seccion = plano[x][y];
        if (seccionesVisitadas[seccion]) {
            return true;
        }
        return false;
    }

    private static void desmarcarSeccion(boolean[] seccionesVisitadas, int[][] plano, int x, int y) {
        int seccion = plano[x][y];
        seccionesVisitadas[seccion] = false;
    }

    private static void marcarSeccion(boolean[] seccionesVisitadas, int[][] plano, int x, int y) {
        int seccion = plano[x][y];
        seccionesVisitadas[seccion] = true;
    }

    private static boolean hemosPasado(int[][] ruta, int x, int y) {
        return (ruta[x][y] == 1);
    }

    private static boolean sePuedeMover(int[][] plano, int x, int y) {
        return ((x >= 0) && (x < plano.length) && (y >= 0) && (y < plano[x].length) && (plano[x][y] != SEPARACION));
    }

    private static boolean seccionesRecorridas(boolean[] seccionesVisitadas) {
        for (int i = 0; i < seccionesVisitadas.length; i++) {
            if (seccionesVisitadas[i] == false) {
                return false;
            }
        }
        return true;
    }

    private static boolean esSalida(int[][] plano, int x, int y) {
        if (plano[x][y] == SALIDA) {
            return true;
        }
        return false;
    }

    private static boolean[] inicializarSecciones(int nSecciones) {
        boolean[] seccionesVisitadas = new boolean[nSecciones + 3];
        for (int i = 0 ; i < 3; i++) {
            seccionesVisitadas[i] = true;
        }
        return seccionesVisitadas;
    }

    private static Point buscarPunto(int[][] plano, int objetivo) {
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                if (plano[i][j] == objetivo) {
                    Point resultado = new Point(i, j);
                    return resultado;
                }
            }
        }
        return null;
    }
}
