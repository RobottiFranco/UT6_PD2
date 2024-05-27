package com.example;

import java.util.Arrays;

public class THash implements IHash {
    private Integer[] table;
    private int capacidadMaxima;

    public THash(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }
        this.table = new Integer[cap];
        this.capacidadMaxima = cap;
        Arrays.fill(this.table, null); // Inicializa todas las posiciones con null
    }

    @Override
    public int buscar(int unaClave) {
        int comparaciones = 0;
        int i = 0;
        int j;
        do {
            j = (this.funcionHashing(unaClave) + i) % capacidadMaxima;
            comparaciones++;
            if (this.table[j] != null && this.table[j] == unaClave) {
                return comparaciones;
            } else {
                i++;
            }
        } while (i < capacidadMaxima && this.table[j] != null);
        return -1; // Si no se encuentra la clave
    }

    @Override
    public int insertar(int unaClave) {
        int comparaciones = 0;
        int i = 0;
        do {
            int j = (this.funcionHashing(unaClave) + i) % capacidadMaxima;
            comparaciones++;
            if (this.table[j] == null) {
                this.table[j] = unaClave;
                return comparaciones;
            } else {
                i++;
            }
        } while (i < capacidadMaxima);
        return comparaciones; // Si no hay espacio disponible
    }

    @Override
    public int funcionHashing(int unaClave) {
        return unaClave % capacidadMaxima;
    }
}
