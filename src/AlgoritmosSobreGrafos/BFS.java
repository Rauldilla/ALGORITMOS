package AlgoritmosSobreGrafos;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nNodos = sc.nextInt();
        int nAristas = sc.nextInt();
        int nodoInicial = sc.nextInt();

        List<Integer>[] grafo = new ArrayList[nNodos + 1];
        inicializarGrafo(grafo);

        for (int i = 0; i < nAristas; i++) {
            int origen = sc.nextInt();
            int destino = sc.nextInt();

            grafo[origen].add(destino);
            grafo[destino].add(origen);
        }

        ArrayList<Integer> recorrido = recorridoAnchura(grafo, nodoInicial);
        mostrarRecorrido(recorrido);
    }

    private static void mostrarRecorrido(ArrayList<Integer> recorrido) {
        for (int i = 0; i < recorrido.size(); i++) {
            System.out.print(recorrido.get(i) + " ");
        }
    }

    private static ArrayList<Integer> recorridoAnchura(List<Integer>[] grafo, int nodoInicial) {
        ArrayList<Integer> recorrido = new ArrayList<>();
        boolean[] visitados = new boolean[grafo.length];
        Queue<Integer> cola = new LinkedList<>();

        recorrido.add(nodoInicial);
        visitados[nodoInicial] = true;
        cola.add(nodoInicial);

        while (!cola.isEmpty()) {
            int aux = cola.remove();
            for (int adj : grafo[aux]) {
                if (!visitados[adj]) {
                    recorrido.add(adj);
                    visitados[adj] = true;
                    cola.add(adj);
                }
            }
        }

        return recorrido;
    }

    private static void inicializarGrafo(List<Integer>[] grafo) {
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
    }
}
