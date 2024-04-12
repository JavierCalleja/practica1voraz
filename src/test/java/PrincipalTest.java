
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PrincipalTest {


    public static void main(String[] args) {

    }


//arraynormal

        @Test
        public void testOrdenacionConArrayNormal() {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(3.2f, 1.5f, 4.7f, 2.6f));
            Principal.ordenacion(paquetes);
            ArrayList<Float> esperado = new ArrayList<>(Arrays.asList(1.5f, 2.6f, 3.2f, 4.7f));
            Assertions.assertEquals(esperado, paquetes, "menor a mayor");
        }

        @Test
        public void testOrdenacionConArrayVacio () {
            ArrayList<Float> paquetes = new ArrayList<>();
            Principal.ordenacion(paquetes);
            ArrayList<Float> esperado = new ArrayList<>();
            Assertions.assertEquals(esperado, paquetes, "array vacio");
        }

        @Test
        public void testOrdenacionConElementosIguales () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(2.0f, 2.0f, 2.0f));
            Principal.ordenacion(paquetes);
            ArrayList<Float> esperado = new ArrayList<>(Arrays.asList(2.0f, 2.0f, 2.0f));
            Assertions.assertEquals(esperado, paquetes, "Elementos duplicados");
        }

        @Test
        public void testOrdenacionConUnSoloElemento () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(1.0f));
            Principal.ordenacion(paquetes);
            ArrayList<Float> esperado = new ArrayList<>(Arrays.asList(1.0f));
            Assertions.assertEquals(esperado, paquetes, "array un elemento");
        }

        //solucion
        @Test
        public void testSolucionExacta () {
            Assertions.assertTrue(Principal.solucion(10, 10), "true");
        }

        @Test
        public void testSolucionNoAlcanzada () {
            Assertions.assertFalse(Principal.solucion(8, 10), "false");
        }

        @Test
        public void testSolucionExcedida () {
            Assertions.assertFalse(Principal.solucion(12, 10), "false");
        }

        @Test
        public void testSolucionConPesoTotalCero () {
            Assertions.assertFalse(Principal.solucion(0, 10), "false");
        }

        @Test
        public void testSolucionConCapacidadCero () {
            Assertions.assertFalse(Principal.solucion(10, 0), "false");
        }


        //voraz
        @Test
        public void testTodosLosPaquetesCaben () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
            int capacidad = 10;
            float resultadoEsperado = 6.0f;
            float resultadoObtenido = Principal.voraz(paquetes, capacidad);
            Assertions.assertEquals(resultadoEsperado, resultadoObtenido, "todos caben");
        }

        @Test
        public void testNingunPaqueteCabe () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(11.0f, 12.0f, 13.0f));
            int capacidad = 10;
            float resultadoEsperado = 0.0f; // Ningún paquete cabe
            float resultadoObtenido = Principal.voraz(paquetes, capacidad);
            Assertions.assertEquals(resultadoEsperado, resultadoObtenido, "Ningún paquete cabe");
        }


        // Caso normal
        @Test
        public void testSeleccion () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
            int indice = 2; // Índice del paquete esperado

            float paqueteSeleccionado = Principal.seleccion(paquetes, indice);

            float paqueteEsperado = 3.0f; // El paquete en el índice 2 es 3.0
            Assertions.assertEquals(paqueteEsperado, paqueteSeleccionado, "El paquete seleccionado no es el esperado");
        }

        // Caso de lista vacía de paquetes
        @Test
        public void testSeleccionConListaVacia () {
            ArrayList<Float> paquetes = new ArrayList<>();

            // Al intentar seleccionar un paquete en una lista vacía, se espera que arroje una excepción
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                Principal.seleccion(paquetes, 0);
            });
        }

        // Caso de capacidad muy pequeña
        @Test
        public void testSeleccionConCapacidadPequena () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
            int indice = 0; // Seleccionar el primer paquete

            float paqueteSeleccionado = Principal.seleccion(paquetes, indice);

            float paqueteEsperado = 1.0f; // Seleccionar el primer paquete de la lista
            Assertions.assertEquals(paqueteEsperado, paqueteSeleccionado, "El paquete seleccionado no es el esperado");
        }

        // Caso de capacidad muy grande
        @Test
        public void testSeleccionConCapacidadGrande () {
            ArrayList<Float> paquetes = new ArrayList<>(Arrays.asList(1000.0f, 2000.0f, 3000.0f));
            int indice = 2; // Seleccionar el último paquete

            float paqueteSeleccionado = Principal.seleccion(paquetes, indice);

            float paqueteEsperado = 3000.0f; // Seleccionar el último paquete de la lista
            Assertions.assertEquals(paqueteEsperado, paqueteSeleccionado, "El paquete seleccionado no es el esperado");
        }

        // Caso normal
        @Test
        public void testFactibilidadNormal () {
            float pesoTotal = 5.0f;
            float paquete = 3.0f;
            int capacidad = 10;

            boolean esFactible = Principal.factibilidad(pesoTotal, paquete, capacidad);

            Assertions.assertFalse(esFactible, "La factibilidad no es la esperada");
        }

        // Caso de capacidad igual a 0
        @Test
        public void testFactibilidadConCapacidadCero () {
            float pesoTotal = 5.0f;
            float paquete = 3.0f;
            int capacidad = 0;

            boolean esFactible = Principal.factibilidad(pesoTotal, paquete, capacidad);

            Assertions.assertFalse(esFactible, "La factibilidad no es la esperada");
        }

        // Caso de paquete mayor que la capacidad restante
        @Test
        public void testFactibilidadConPaqueteExcediendoCapacidad () {
            float pesoTotal = 8.0f;
            float paquete = 5.0f;
            int capacidad = 5;

            boolean esFactible = Principal.factibilidad(pesoTotal, paquete, capacidad);

            Assertions.assertFalse(esFactible, "La factibilidad no es la esperada");
        }

    }




