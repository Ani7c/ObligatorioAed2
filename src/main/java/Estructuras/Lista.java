package Estructuras;

public class Lista<T extends Comparable<T>> {

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

    public void imprimirDatos(NodoLista<T> nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDato());
            imprimirDatos(nodo.getSig());
        }
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
