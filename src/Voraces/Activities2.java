import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Activities2 {

    private static class Actividad implements Comparable{
        private int horaIni;
        private int horaFin;
        private int tiempoDescanso;
        private int ratio;

        public Actividad(int horaIni, int horaFin, int tiempoDescanso) {
            this.horaIni = horaIni;
            this.horaFin = horaFin;
            this.tiempoDescanso = tiempoDescanso;
            this.ratio = horaFin + tiempoDescanso;
        }

        @Override
        public int compareTo(Object o) {
            Actividad a = (Actividad) o;
            return Integer.compare(this.ratio, a.ratio);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nCasos = sc.nextInt();
        int actsProgramadas;

        List<Actividad>[] calendario = new ArrayList[nCasos];
        inicializarCalendario(calendario);

        for (int i = 0; i < nCasos; i++) {
            actsProgramadas = sc.nextInt();
            for (int j = 0; j < actsProgramadas; j++) {
                Actividad act = new Actividad(sc.nextInt(), sc.nextInt(), sc.nextInt());
                calendario[i].add(act);
            }
            Collections.sort(calendario[i]);
        }

        organizarActs(calendario);
    }

    private static void organizarActs(List<Actividad>[] calendario) {
        List<Integer> solucion = new ArrayList<>();
        int contador;
        int index;

        for (int i = 0; i < calendario.length; i++) {
            contador = 1;
            index = 0;
            for (int j = 1; j < calendario[i].size(); j++) {
                if (calendario[i].get(index).ratio <= calendario[i].get(j).horaIni) {
                    contador++;
                    index = j;
                }
            }
            solucion.add(contador);
        }

        mostrarSolucion(solucion);
    }

    private static void mostrarSolucion(List<Integer> solucion) {
        for (int i = 0; i < solucion.size(); i++) {
            System.out.println(solucion.get(i));
        }
    }

    private static void inicializarCalendario(List<Actividad>[] calendario) {
        for (int i = 0; i < calendario.length; i++) {
            calendario[i] = new ArrayList<>();
        }
    }
}
