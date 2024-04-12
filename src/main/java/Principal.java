import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int capacidad = obtenerCapacidad();
        ArrayList<Float> paquetes = obtenerPaquetes();
        voraz(paquetes, capacidad);
    }

    public static int obtenerCapacidad() {
        System.out.println("Capacidad máxima del camión?");
        return scanner.nextInt();
    }

    public static ArrayList<Float> obtenerPaquetes() {
        ArrayList<Float> paquetes = new ArrayList<>();
        System.out.println("Número total de paquetes?");
        int numPaquetes = scanner.nextInt();

        for (int i = 0; i < numPaquetes; i++) {
            System.out.println("Peso del paquete " + (i + 1) + ":");
            float peso = scanner.nextFloat();
            paquetes.add(peso);
        }

        System.out.println("Entrada:");
        System.out.println(paquetes);
        return paquetes;
    }

    public static float voraz(ArrayList<Float> paquetes, int capacidad) {
        boolean[] elegidos = new boolean[paquetes.size()];
        ordenacion(paquetes);
        int i = 0;
        float pesoTotal = 0;
        boolean factible = true;
        while (i < paquetes.size() && !solucion(pesoTotal, capacidad) && factible) {
            float paquete = seleccion(paquetes, i);
            if (factibilidad(pesoTotal, paquete, capacidad)) {
                elegidos[i] = true;
                pesoTotal += paquete;
                i++;
            } else {
                factible = false;
            }
        }
        mostrarSolucion(paquetes, elegidos);
        return pesoTotal;
    }

    public static void ordenacion(ArrayList<Float> paquetes) {
        paquetes.sort(null);
        System.out.println("Paquetes ordenados crecientemente por peso:");
        System.out.println(paquetes);
    }



    public static float seleccion(ArrayList<Float> paquetes, int i) {
        return paquetes.get(i);
    }

    public static boolean factibilidad(float pesoTotal, float paquete, int capacidad) {
        return pesoTotal + paquete <= capacidad;
    }

    public static boolean solucion(float pesoTotal, int capacidad) {
        return pesoTotal == capacidad;
    }

    public static void mostrarSolucion(ArrayList<Float> paquetes, boolean[] elegidos) {
        float pesoTotal = 0;
        int factura = 0;
        System.out.println("Paquetes transportados:");
        for (int i = 0; i < paquetes.size(); i++) {
            if (elegidos[i]) {
                System.out.println("Paquete " + (i + 1) + ": Peso " + paquetes.get(i));
                factura += 40;
                pesoTotal += paquetes.get(i);
            }
        }
        System.out.println("Factura total: " + factura + " euros");
        System.out.println("Peso total transportado: " + pesoTotal + " toneladas");
    }
}
