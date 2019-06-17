import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeleccionEnemigos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nEnemigos = sc.nextInt();

        List<Integer> enemigos = new ArrayList<>();
        for (int i = 0; i < nEnemigos; i++) {
            enemigos.add(sc.nextInt());
        }

        int nConsultas = sc.nextInt();
        for (int i = 0; i < nConsultas; i++) {
            int min = sc.nextInt();
            int max = sc.nextInt();

            int posicion1 = busquedaBinaria(enemigos, min, 0);
            int posicion2 = busquedaBinaria(enemigos, max, 0);

            System.out.println(posicion1 + " " + posicion2);
        }
    }

    private static int busquedaBinaria(List<Integer> enemigos, int numero, int index) {
        int centro = enemigos.size() / 2;

        if (enemigos.get(centro) == numero) {
            return centro + index;
        } else {
            if (enemigos.get(centro) < numero) {
                return busquedaBinaria(enemigos.subList(centro + 1, enemigos.size()), numero, centro + index + 1);
            } else {
                return busquedaBinaria(enemigos.subList(0, centro), numero, index);
            }
        }
    }
}
