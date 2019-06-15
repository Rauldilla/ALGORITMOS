package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Thanos {

    private static final int TITAN = 0;
    private static int nRutas;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nPlanetas = sc.nextInt();
        int nConexiones = sc.nextInt();

        List<Integer>[] sistema = new ArrayList[nPlanetas];
        inicializarSistema(sistema);

        for (int i = 0; i < nConexiones; i++) {
            int origen = sc.nextInt();
            int destino = sc.nextInt();

            sistema[origen].add(destino);
            sistema[destino].add(origen);
        }

        nRutas = 0;
        buscarRutas(sistema, nPlanetas);
        System.out.println(nRutas);
    }

    private static void buscarRutas(List<Integer>[] sistema, int nPlanetas) {
        boolean[] visitados = new boolean[nPlanetas];
        visitados[TITAN] = true;

        buscadorRecursivo(sistema, visitados, TITAN);
    }

    private static void buscadorRecursivo(List<Integer>[] sistema, boolean[] visitados, int planeta) {

        if (esAdyacente(sistema[planeta], TITAN) && todosVisitados(visitados)) {
            nRutas++;
        } else {
            for (int adj : sistema[planeta]) {
                if (!visitados[adj]) {
                    visitados[adj] = true;
                    buscadorRecursivo(sistema, visitados, adj);

                    // Anterior buscador ha fracasado
                    visitados[adj] = false;
                }
            }
        }
    }

    private static boolean todosVisitados(boolean[] visitados) {
        for (int i = 0; i < visitados.length; i++) {
            if (visitados[i] == false) {
                return false;
            }
        }
        return true;
    }

    private static boolean esAdyacente(List<Integer> adyacentes, int titan) {
        for (int i = 0; i < adyacentes.size(); i++) {
            if (adyacentes.get(i) == titan) {
                return true;
            }
        }
        return false;
    }

    private static void inicializarSistema(List<Integer>[] sistema) {
        for (int i = 0; i < sistema.length; i++) {
            sistema[i] = new ArrayList<>();
        }
    }
}
