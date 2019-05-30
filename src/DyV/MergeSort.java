package DyV;

import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nElementos = sc.nextInt();
        ArrayList<Integer> lista = new ArrayList<>();

        for (int i = 0; i < nElementos; i++) {
            lista.add(sc.nextInt());
        }

        ArrayList<Integer> resultado = mergesort(lista);
        mostrarLista(resultado);
    }

    private static void mostrarLista(ArrayList<Integer> resultado) {
        for (int i = 0; i < resultado.size(); i++) {
            System.out.print(resultado.get(i) + " ");
        }
    }

    private static ArrayList<Integer> mergesort(ArrayList<Integer> lista) {
        ArrayList<Integer> izquierda = new ArrayList<>();
        ArrayList<Integer> derecha = new ArrayList<>();
        int centro;

        if (lista.size() == 1) { // Caso base, lista de UN elemento
            return lista;
        } else {
            centro = lista.size() / 2;

            // Se parte la lista en dos, obteniendo lado izquierdo y derecho
            for (int i = 0; i < centro; i++) {
                izquierda.add(lista.get(i));
            }
            for (int j = centro; j < lista.size(); j++) {
                derecha.add(lista.get(j));
            }

            // Se le aplica el algoritmo a las dos nuevas listas
            izquierda = mergesort(izquierda);
            derecha = mergesort(derecha);

            merge(izquierda, derecha, lista);
        }
        return lista;
    }

    private static void merge(ArrayList<Integer> izquierda, ArrayList<Integer> derecha, ArrayList<Integer> lista) {
        int index = 0;
        int indexIzq = 0;
        int indexDer = 0;

        // Comparacion de los elementos de las dos listas hasta que UNA de ellas se recorre por completo
        while ((indexIzq < izquierda.size()) && (indexDer < derecha.size())) {
            if (izquierda.get(indexIzq) <= derecha.get(indexDer)) {
                lista.set(index, izquierda.get(indexIzq));
                indexIzq++;
            } else {
                lista.set(index, derecha.get(indexDer));
                indexDer++;
            }
            index++;
        }

        // Se añaden al resultado los elementos de la lista que no se habia recorrido por completo
        if (indexIzq == izquierda.size()) {
            for (int i = indexDer; i < derecha.size(); i++) {
                lista.set(index, derecha.get(i));
                index++;
            }
        } else {
            for (int j = indexIzq; j < izquierda.size(); j++) {
                lista.set(index, izquierda.get(j));
                index++;
            }
        }
    }
}
