package com.example;

import java.util.HashMap;

public class THash implements IHash {
    private HashMap<Integer, Integer> map;
    private int capacidadMaxima;

    public THash(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }
        this.map = new HashMap<>(cap);
        this.capacidadMaxima = cap;
    }

    @Override
    public int buscar(int unaClave) {
        int i = 0;
        int j;
        do {
            j = this.funcionHashing(unaClave) + i;
            if (this.map.get(j) != null && this.map.get(j) == unaClave) {
                return j;
            } else {
                i++;
            }
        } while (i < capacidadMaxima && this.map.get(j) != null);
        return -1;
    }

    @Override
    public int insertar(int unaClave) {
        int i = 0;
        do {
            int j = this.funcionHashing(unaClave) + i;
            if (this.map.get(j) == null) {
                this.map.put(j, unaClave);
                return j;
            } else {
                i++;
            }
        } while (i < capacidadMaxima);
        return -1;
    }

    @Override
    public int funcionHashing(int unaClave) {
        int suma = 0;

        String aux = Integer.toString(unaClave);

        while (unaClave >= 1) {
            suma += unaClave % 10;
            unaClave = unaClave / 10;
        }

        suma = suma * aux.length();
        if (capacidadMaxima == 0) {
            throw new ArithmeticException("El tamaño del mapa es cero, no se puede dividir por cero.");
        }
        return suma % capacidadMaxima;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int n) {
        n++;
        if (n % 2 == 0) {
            n++;
        }
        for (; !isPrime(n); n += 2) {

        }
        return n;
    }
}
