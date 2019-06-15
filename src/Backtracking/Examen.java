package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Examen {

    private static class Estudiante {
        private int modelo;
        private ArrayList<Estudiante> adyacentes;

        public Estudiante() {
            this.modelo = -1;
            this.adyacentes = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nEstudiantes = sc.nextInt();
        int nRelaciones = sc.nextInt();
        int nModelos = sc.nextInt();

        Estudiante[] clase = new Estudiante[nEstudiantes];
        inicializarClase(clase);

        for (int i = 0; i < nRelaciones; i++) {
            int origen = sc.nextInt();
            int destino = sc.nextInt();

            clase[origen].adyacentes.add(clase[destino]);
            clase[destino].adyacentes.add(clase[origen]);
        }

        boolean resultado = repartirModelos(clase, nModelos);
        if (resultado) {
            System.out.println("OK");
        } else {
            System.out.println("NO HAY SUFICIENTE");
        }

    }

    private static void inicializarClase(Estudiante[] clase) {
        for (int i = 0; i < clase.length; i++) {
            clase[i] = new Estudiante();
        }
    }

    private static boolean repartirModelos(Estudiante[] clase, int nModelos) {

        if (todosTienenModelo(clase)) {
            return true;
        } else {
            for (int i = 0; i < clase.length; i++) {
                if (noTieneModelo(clase[i])) {
                    for (int modelo = 0; modelo < nModelos; modelo++) {
                        if (!mismoModeloAdy(clase[i], modelo)) {
                            clase[i].modelo = modelo;

                            if (repartirModelos(clase, nModelos)) {
                                return true;
                            } else {
                                clase[i].modelo = -1;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private static boolean noTieneModelo(Estudiante estudiante) {
        if (estudiante.modelo == -1) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean mismoModeloAdy(Estudiante estudiante, int modelo) {
        for (int i = 0; i < estudiante.adyacentes.size(); i++) {
            if (estudiante.adyacentes.get(i).modelo == modelo) {
                return true;
            }
        }
        return false;
    }

    private static boolean todosTienenModelo(Estudiante[] clase) {
        for (int i = 0; i < clase.length; i++) {
            if (clase[i].modelo == -1) {
                return false;
            }
        }
        return true;
    }

}
