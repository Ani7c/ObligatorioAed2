package Estructuras;

import interfaz.Retorno;

public class ABB<T extends Comparable<T>>{

    protected NodoABB<T> raiz;

    public void insertar(T dato) {
        if (raiz == null)
            raiz = new NodoABB<>(dato);
        else
            insertarRec(dato, raiz);
    }

    private void insertarRec(T dato, NodoABB<T> nodo) {
        if (nodo.dato.compareTo(dato) > 0) {
            if (nodo.izq == null)
                nodo.izq = new NodoABB<>(dato);
            else
                insertarRec(dato, nodo.izq);
        } else if (nodo.dato.compareTo(dato) < 0) {
            if (nodo.der == null)
                nodo.der = new NodoABB<>(dato);
            else
                insertarRec(dato, nodo.der);
        }
    }

    public T buscar(T dato) {
        return buscar(dato, raiz);
    }

    private T buscar(T dato, NodoABB<T> nodo) {
        if (nodo != null) {
            if (nodo.dato.equals(dato))
                return nodo.dato;
            else if (nodo.dato.compareTo(dato) > 0) {
                return buscar(dato, nodo.izq);
            } else if (nodo.dato.compareTo(dato) < 0) {
                return buscar(dato, nodo.der);
            }
        }
        return null;
    }

    public Retorno buscarconretorno(T dato) {
        return buscarconretorno(dato, raiz,0);
    }
    // Método auxiliar para la búsqueda recursiva
    private Retorno buscarconretorno(T dato, NodoABB<T> nodo, int nodosRecorridos) {
        if (nodo != null) {
            nodosRecorridos++; // Contamos cada nodo recorrido
            if (nodo.dato.equals(dato)) {
                // Si encuentra el dato, retorna el valorString con el toString del dato y el número de nodos recorridos
                return Retorno.ok(nodosRecorridos, nodo.dato.toString());
            } else if (nodo.dato.compareTo(dato) > 0) {
                return buscarconretorno(dato, nodo.izq, nodosRecorridos);
            } else {
                return buscarconretorno(dato, nodo.der, nodosRecorridos);
            }
        }
        // Si no se encuentra el dato, retorna null y el número de nodos recorridos
        return  Retorno.ok(nodosRecorridos,null);
    }
    public String listarAscendenteString() {
        return listarAscendenteString(raiz);
    }

    private String listarAscendenteString(NodoABB<T> nodo) {
        if (nodo != null) {
            return listarAscendenteString(nodo.izq) + "|" + nodo.dato + "|" + listarAscendenteString(nodo.der);
        }
        return "";
    }


    protected class NodoABB<Q> {
        protected Q dato;
        protected NodoABB<Q> izq;
        protected NodoABB<Q> der;

        public NodoABB(Q dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "NodoABB[" + dato + "]";
        }
    }
}
