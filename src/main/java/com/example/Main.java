package com.example;

public class Main {
    public static void main(String[] args) {
        int elementosEnHash = 200;
        int hasta70 = 70; // 70%
        int hasta90 = 90; // 90%
        int hasta99 = 99; // 99%

        String[] fileLeer = ManejadorArchivosGenerico.leerArchivo("claves_insertar.txt");
        String[] fileEscribir = ManejadorArchivosGenerico.leerArchivo("claves_buscar.txt");

        System.out.println("Resultados de las pruebas:");

        for (int factorDeCarga = hasta70; factorDeCarga <= hasta90; factorDeCarga += 5) {
            simulacion(elementosEnHash, factorDeCarga, fileLeer, fileEscribir);
        }

        for (int factorDeCarga = hasta90 + 1; factorDeCarga <= hasta99; factorDeCarga += 1) {
            simulacion(elementosEnHash, factorDeCarga, fileLeer, fileEscribir);
        }
    }

    private static void simulacion(int elementosEnHash, int factorDeCarga, String[] fileLeer, String[] fileEscribir) {

        int tamanoTabla = (int) Math.ceil((double) elementosEnHash / (factorDeCarga / 100.0));
        THash hashTable = new THash(tamanoTabla);

        int ComparacionesInsertar = 0;
        int comparacionesBuscarCorrectas = 0;
        int BuscarIncorrectas = 0;
        int cantBusquedasCorrectas = 0;

        for (String string : fileLeer) {
            ComparacionesInsertar += hashTable.insertar(Integer.parseInt(string));
        }

        for (String string : fileEscribir) {
            int x = hashTable.buscar(Integer.parseInt(string));
            if (x != -1) {
                comparacionesBuscarCorrectas += x;
                cantBusquedasCorrectas++;
            } else {
                BuscarIncorrectas++;
            }
        }

        int promedioComparacionesInsertar = ComparacionesInsertar / elementosEnHash;
        int promedioComparacionesCorrectasBuscar = comparacionesBuscarCorrectas / cantBusquedasCorrectas;
        System.out.println("=====================");
        System.out.println("Probando con factor de carga: " + factorDeCarga + "%");
        System.out.println("Promedio de comparaciones para insertar: " + promedioComparacionesInsertar);
        System.out.println("Promedio de comparaciones en búsquedas exitosas: " + promedioComparacionesCorrectasBuscar);
        System.out
                .println("búsquedas sin éxito: " + BuscarIncorrectas);
    }
}
