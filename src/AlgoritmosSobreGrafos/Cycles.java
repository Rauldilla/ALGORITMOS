package AlgoritmosSobreGrafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cycles {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nNodos = sc.nextInt();
        int nAristas = sc.nextInt();

        if (nNodos == 0) {
            System.out.println("false");
        } else {
            List<Integer>[] grafo = new ArrayList[nNodos + 1];
            inicializarGrafo(grafo);

            for (int i = 0; i < nAristas; i++) {
                int origen = sc.nextInt();
                int destino = sc.nextInt();

                grafo[origen].add(destino);
                grafo[destino].add(origen);
            }

            boolean resultado = buscarCiclos(grafo);
            System.out.println(resultado);
        }
    }

    private static boolean buscarCiclos(List<Integer>[] grafo) {
        boolean[] visitados = new boolean[grafo.length];

        for (int i = 0; i < grafo.length; i++) {
            if (DFSrecursivo(grafo, i, -1, visitados)) {
                return true;
            }
            visitados = new boolean[grafo.length]; //Como se comprueban todos los nodos debe resetearse la lista de visitados para cada uno
        }
        return false;
    }

    private static boolean DFSrecursivo(List<Integer>[] grafo, int nodo, int padre, boolean[] visitados) {

        visitados[nodo] = true;

        for (int adj : grafo[nodo]) {
            if (!visitados[adj]) {
                if (DFSrecursivo(grafo, adj, nodo, visitados)) {
                    return true;
                }
            } else if (adj != padre) {  //Caso de que se haya visitado y ADEMÁS no sea el padre -> Ciclo detectado
                return true;
            }
        }
        return false;
    }

    private static void inicializarGrafo(List<Integer>[] grafo) {
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
    }
}
