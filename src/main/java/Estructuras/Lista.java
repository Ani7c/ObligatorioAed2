package Estructuras;

import interfaz.ILista;

import java.util.Iterator;

public class Lista<T> implements ILista <T>{

    protected NodoLista<T> inicio;
    protected int largo;

    public Lista() {
        this.inicio = null;
        this.largo = 0;
    }

    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }
    public void insertarOrdenado(T dato) {
        NodoLista<T> nuevoNodo = new NodoLista<>(dato);

        // Caso 1: Lista vacía o el dato debe ser el nuevo primer elemento
        if (esVacia() || ((Comparable<T>) dato).compareTo(inicio.getDato()) < 0) {
            nuevoNodo.sig = inicio;
            inicio = nuevoNodo;
        } else {
            // Caso 2: Insertar en la posición correcta (no al principio)
            NodoLista<T> actual = inicio;
            while (actual.sig != null && ((Comparable<T>) dato).compareTo(actual.sig.getDato()) > 0) {
                actual = actual.sig;
            }
            // Insertar el nuevo nodo en la posición encontrada
            nuevoNodo.sig = actual.sig;
            actual.sig = nuevoNodo;
        }
        largo++;
    }

    @Override
    public void borrar(T dato) {

    }

    public T recuperar(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }


    public int largo() {
        return largo;
    }

    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }
    public boolean esLlena() {
        return false;
    }

    @Override
    public void imprimirDatos() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null){
                System.out.print(aux.getDato() + " -> ");
            }else{
                System.out.print(aux.getDato());
            }
            aux = aux.getSig();
        }
        System.out.println();
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.dato;
                aux = aux.sig;
                return dato;
            }

            @Override
            public void remove() {
            }

        };
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoLista<T> aux = inicio;

        while (aux != null) {
            sb.append(aux.getDato().toString()); // Llama al toString de Sucursal
            aux = aux.getSig();
            if (aux != null) {
                sb.append("|");
            }
        }
        return sb.toString();
    }

    class NodoLista<T> {
        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }
}
