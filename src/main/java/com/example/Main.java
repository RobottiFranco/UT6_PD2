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

        int totalComparacionesInsertar = 0;
        int totalComparacionesBuscar = 0;
        int busquedasExitosas = 0;
        int busquedasSinExito = 0;

        for (String string : fileLeer) {
            totalComparacionesInsertar += hashTable.insertar(Integer.parseInt(string));
        }

        for (String string : fileEscribir) {
            int comparaciones = hashTable.buscar(Integer.parseInt(string));
            if (comparaciones != -1) {
                totalComparacionesBuscar += comparaciones;
                busquedasExitosas++;
            } else {
                busquedasSinExito++;
            }
        }

        int promedioComparacionesInsertar = totalComparacionesInsertar / elementosEnHash;
        int promedioComparacionesBuscar = busquedasExitosas > 0 ? totalComparacionesBuscar / busquedasExitosas : 0;

        System.out.println("=====================");
        System.out.println("Probando con factor de carga: " + factorDeCarga + "%");
        System.out.println("Promedio de comparaciones para insertar: " + promedioComparacionesInsertar);
        System.out.println("Promedio de comparaciones en búsquedas exitosas: " + promedioComparacionesBuscar);
        System.out.println("Búsquedas sin éxito: " + busquedasSinExito);
    }
}
