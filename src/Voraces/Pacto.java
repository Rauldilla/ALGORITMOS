package Voraces;

import java.util.*;

public class Pacto {

    private static class Candidato implements Comparable{
        private String nombre;
        private double nDiputados;
        private double perjuicio;
        private double ratio;

        public Candidato(String nombre, double nDiputados, double perjuicio) {
            this.nombre = nombre;
            this.nDiputados = nDiputados;
            this.perjuicio = perjuicio;
            this.ratio = perjuicio / nDiputados;
        }

        @Override
        public int compareTo(Object o) {
            Candidato c = (Candidato) o;
            return Double.compare(this.ratio, c.ratio);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nPoliticos = sc.nextInt();
        List<Candidato> parlamento = new ArrayList<>();

        for (int i = 0; i < nPoliticos; i++) {
            Candidato c = new Candidato(sc.next(), sc.nextInt(), sc.nextInt());
            parlamento.add(c);
        }

        Candidato ganador = buscarGanador(parlamento);
        Collections.sort(parlamento);

        pactometro(parlamento, ganador);
    }

    private static void pactometro(List<Candidato> parlamento, Candidato ganador) {
        List<Candidato> solucion = new ArrayList<>();
        int mayoriaAbsoluta = calcularMayoria(parlamento);
        int contadorDips = 0;
        int index = 0;

        contadorDips += ganador.nDiputados;

        while ((contadorDips < mayoriaAbsoluta) && (index < parlamento.size())) {
            if (!parlamento.get(index).nombre.equals(ganador.nombre)) {
                if ((contadorDips + parlamento.get(index).nDiputados) <= mayoriaAbsoluta) {
                    solucion.add(parlamento.get(index));
                    contadorDips += parlamento.get(index).nDiputados;
                } else {
                    Candidato cand = partirCandidato(parlamento.get(index), contadorDips, mayoriaAbsoluta);
                    solucion.add(cand);
                    contadorDips += cand.nDiputados;
                }
            }
            index++;
        }

        // Aunque en el enunciado no aparece establecido, en el EXAMEN se pedia a mayores que
        // los elementos de la solucion estuviesen ordenados alfabeticamente
        Collections.sort(solucion, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato o1, Candidato o2) {
                return o1.nombre.compareTo(o2.nombre);
            }
        });

        mostrarResultado(ganador, solucion);
    }

    private static void mostrarResultado(Candidato ganador, List<Candidato> solucion) {
        System.out.println(ganador.nombre);
        for (int i = 0; i < solucion.size(); i++) {
            System.out.println(solucion.get(i).nombre);
        }
    }

    private static Candidato partirCandidato(Candidato candidato, int contadorDips, int mayoriaAbsoluta) {
        String nombreNuevo = candidato.nombre;
        double nDiputadosNuevo = mayoriaAbsoluta - contadorDips;
        double porcentaje = nDiputadosNuevo / candidato.nDiputados;
        double perjuicioNuevo = candidato.perjuicio * porcentaje;

        Candidato c = new Candidato(nombreNuevo, nDiputadosNuevo, perjuicioNuevo);
        return c;
    }

    private static int calcularMayoria(List<Candidato> parlamento) {
        int contadorDiputados = 0;
        for (int i = 0; i < parlamento.size(); i++) {
            contadorDiputados += parlamento.get(i).nDiputados;
        }
        return (contadorDiputados / 2) + 1;
    }

    private static Candidato buscarGanador(List<Candidato> parlamento) {
        Candidato ganador = parlamento.get(0);

        for (int i = 1; i < parlamento.size(); i++) {
            if (parlamento.get(i).nDiputados > ganador.nDiputados) {
                ganador = parlamento.get(i);
            }
        }
        return ganador;
    }
}
