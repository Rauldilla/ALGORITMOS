package Voraces;

import java.util.*;

public class DarkSouls {

    public static class Objeto implements Comparable{
        private String nombre;
        private double peso;
        private double defensa;
        private double ratio;

        public Objeto(String nombre, double peso, double defensa) {
            this.nombre = nombre;
            this.peso = peso;
            this.defensa = defensa;
            this.ratio = defensa/peso;
        }

        @Override
        public int compareTo(Object o) {
            Objeto obj = (Objeto) o;
            return Double.compare(obj.ratio, this.ratio);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cantidadEquipo = sc.nextInt();
        double pesoMax = sc.nextInt();
        String modoJuego = sc.next();

        ArrayList<Objeto> inventario = new ArrayList<>();

        for (int i = 0; i < cantidadEquipo; i++) {
            String nombre = sc.next();
            double peso = sc.nextInt();
            double defensa = sc.nextInt();

            Objeto o = new Objeto(nombre, peso, defensa);
            inventario.add(o);
        }
        Collections.sort(inventario);

        if (modoJuego.equals("ligero")) {
            double peso = 0.5 * pesoMax;
            optimizarEquipamiento(inventario, peso);
        } else if (modoJuego.equals("medio")) {
            double peso = 0.75 * pesoMax;
            optimizarEquipamiento(inventario, peso);
        } else if (modoJuego.equals("pesado")) {
            optimizarEquipamiento(inventario, pesoMax);
        }
    }

    private static void optimizarEquipamiento(ArrayList<Objeto> inventario, double peso) {
        ArrayList<Objeto> solucion = new ArrayList<>();
        double contadorPeso = 0;
        double contadorDefensa = 0;
        int index = 0;

        while (contadorPeso < peso) {
            Objeto obj = inventario.get(index);
            if (sePuedeEquipar(obj, contadorPeso, peso)) {
                solucion.add(obj);
                contadorDefensa += obj.defensa;
                contadorPeso += obj.peso;
            } else {
                if (contadorPeso < peso) {
                    Objeto objetoPartido = partirObjeto(obj, contadorPeso, peso);

                    solucion.add(objetoPartido);
                    contadorDefensa += objetoPartido.defensa;
                    contadorPeso += objetoPartido.peso;
                }
            }
            index++;
        }

        mostrarResultado(contadorDefensa, solucion);
    }

    private static Objeto partirObjeto(Objeto obj, double contadorPeso, double peso) {
        String nuevoNombre = obj.nombre;
        double nuevoPeso = peso - contadorPeso;
        double proporcionPeso = nuevoPeso / obj.peso;
        double nuevaDefensa = obj.defensa * proporcionPeso;

        Objeto nuevoObjeto = new Objeto(nuevoNombre, nuevoPeso, nuevaDefensa);
        return nuevoObjeto;
    }

    private static boolean sePuedeEquipar(Objeto objeto, double contadorPeso, double peso) {
        if ((contadorPeso + objeto.peso) <= peso) {
            return true;
        } else {
            return false;
        }
    }

    private static void mostrarResultado(double defensaTotal, List<Objeto> resultado) {

        Collections.sort(resultado, new Comparator<Objeto>() {
            @Override
            public int compare(Objeto o1, Objeto o2) {
                return o1.nombre.compareTo(o2.nombre);
            }
        });

        String defensaTotalString = String.format("%,.2f", defensaTotal);
        System.out.println(defensaTotalString);
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println(resultado.get(i).nombre);
        }
    }
}
