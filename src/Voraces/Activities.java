package Voraces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Activities {

    public static class Actividad implements Comparable{
        private int horaIni;
        private int horaFin;

        public Actividad(int horaIni, int horaFin) {
            this.horaIni = horaIni;
            this.horaFin = horaFin;
        }

        @Override
        public int compareTo(Object o) {
            Actividad act = (Actividad) o;

            return Integer.compare(this.horaFin, act.horaFin);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nCasos = sc.nextInt();
        List<Actividad>[] calendario = new ArrayList[nCasos];
        inicializarCalendario(calendario);

        for (int i = 0; i < nCasos; i++) {
            int nActividades = sc.nextInt();
            for (int j = 0; j < nActividades; j++) {
                int horaInicio = sc.nextInt();
                int horaFin = sc.nextInt();
                Actividad nuevaAct = new Actividad(horaInicio, horaFin);

                calendario[i].add(nuevaAct);
            }
            Collections.sort(calendario[i]);
        }

        ArrayList<Integer> resultado = optimizarActividades(calendario);
        mostrarResultado(resultado);
    }

    private static void mostrarResultado(ArrayList<Integer> resultado) {
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println(resultado.get(i));
        }
    }

    private static ArrayList<Integer> optimizarActividades(List<Actividad>[] calendario) {
        ArrayList<Integer> resultado = new ArrayList<>();
        int contador; //Contador de las actividades a las que podremos ir
        int actSeleccionada;  //Ultima actividad que hemos seleccionado

        for (int i = 0; i < calendario.length; i++) {
            contador = 1;
            actSeleccionada = 0; //Como minimo siempre se va a la primera actividad disponible
            for (int j = 1; j < calendario[i].size(); j++) {
                if (calendario[i].get(actSeleccionada).horaFin <= calendario[i].get(j).horaIni) {
                    contador++;
                    actSeleccionada = j;
                }
            }
            resultado.add(contador);
        }
        return resultado;
    }

    private static void inicializarCalendario(List<Actividad>[] calendario) {
        for (int i = 0; i < calendario.length; i++) {
            calendario[i] = new ArrayList<>();
        }
    }
}
