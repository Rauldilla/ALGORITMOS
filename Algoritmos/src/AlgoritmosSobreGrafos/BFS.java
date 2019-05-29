package AlgoritmosSobreGrafos;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nNodos = sc.nextInt();
        int nAristas = sc.nextInt();
        int nodoInicio = sc.nextInt();

        List<Integer>[] grafo = new List[nNodos + 1];
        inicializar(grafo);

        for (int i = 0; i < nAristas; i++) {
            int origen = sc.nextInt();
            int destino = sc.nextInt();

            grafo[origen].add(destino);
            grafo[destino].add(origen);
        }


        recorridoAnchura(grafo, nodoInicio, nNodos);
    }

    private static void recorridoAnchura(List<Integer>[] grafo, int nodoInicio, int nNodos) {
        ArrayList<Integer> resultado = new ArrayList<>();
        boolean[] visitados = new boolean[nNodos + 1];
        Queue<Integer> cola = new LinkedList<>();

        resultado.add(nodoInicio);
        visitados[nodoInicio] = true;
        cola.add(nodoInicio);

        while (!cola.isEmpty()) {
            int aux = cola.remove();
            for (int adj : grafo[aux]) {
                if (!visitados[adj]) {
                    resultado.add(adj);
                    visitados[adj] = true;
                    cola.add(adj);
                }
            }
        }

        for (int i = 0; i < resultado.size(); i++) {
            System.out.print(resultado.get(i) + " ");
        }
    }

    private static void inicializar(List<Integer>[] grafo) {
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
    }
}