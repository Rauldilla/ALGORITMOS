package DyV;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DarkSoulsDyV {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //region RECOGIDA DE DATOS
        long nEnemigos = sc.nextLong();
        List<Long> oleada = new ArrayList<>();
        List<Long> sumasAcumuladasNiveles = new ArrayList<>(); //Esto hace que el algoritmo sea mas rapido a la hora de dar la solucion

        long suma = 0;
        for (int i = 0; i < nEnemigos; i++) {
            long nivel = sc.nextLong();
            oleada.add(nivel);

            suma += nivel;
            sumasAcumuladasNiveles.add(suma);
        }

        long nPruebas = sc.nextLong();
        long nivelAstora;
        //endregion

        long acumulado = 0; // Al ser una busqueda binaria, trabajaremos con arrays cada vez mas pequeños, por lo que necesitamos una variable para
                            // mantener una referencia al indice del array inicial, en este caso oleada
        for (int i = 0; i < nPruebas; i++) {
            nivelAstora = sc.nextLong();
            int limite = (int) busquedaBinaria(oleada, nivelAstora, acumulado);
            
            if (limite == -1) {
                System.out.println(0 + " " + 0);
            } else {
                System.out.println(limite + 1 + " " + sumasAcumuladasNiveles.get(limite));
            }
        }
    }

    private static long busquedaBinaria(List<Long> oleada, long nivelAstora, long acumulado) {
        int centro = oleada.size() / 2;

        if (!oleada.isEmpty()) {
            if (oleada.get(centro) == nivelAstora) { //Caso de encontrarlo justo en el medio
                return centro + acumulado;
            } else if (oleada.get(centro) > nivelAstora) { //Caso de que el nivel que buscamos este en el lado izquierdo de la lista
                return busquedaBinaria(oleada.subList(0, centro), nivelAstora, acumulado);
            } else {
                return busquedaBinaria(oleada.subList(centro + 1, oleada.size()), nivelAstora, centro + acumulado + 1);
            }
        } else {
            return acumulado - 1;
        }
    }

}
