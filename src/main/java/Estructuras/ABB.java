package Estructuras;

import interfaz.Retorno;

public class ABB<T extends Comparable<T>>{

    protected NodoABB<T> raiz;
    protected int cantidad = 0;

    public int size(){
        return cantidad;
    }

    public boolean existe(T dato) {
        return existe(dato, raiz);
    }
    private boolean existe(T dato, NodoABB<T> nodo) {
        // Verifica si el nodo actual es nulo
        if (nodo == null) {
            return false; // El dato no se encuentra en el árbol
        }

        // Compara el dato con el dato del nodo actual
        if (dato.equals(nodo.dato)) {
            return true; // El dato fue encontrado
        } else if (dato.compareTo(nodo.dato) < 0) {
            return existe(dato, nodo.izq); // Busca en el subárbol izquierdo
        } else {
            return existe(dato, nodo.der); // Busca en el subárbol derecho
        }
    }
    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoABB<>(dato);
            cantidad++;
        }else
            insertarRec(dato, raiz);
    }

    private void insertarRec(T dato, NodoABB<T> nodo) {
        if (nodo.dato.compareTo(dato) > 0) {
            if (nodo.izq == null) {
                nodo.izq = new NodoABB<>(dato);
                cantidad++;
            }else
                insertarRec(dato, nodo.izq);
        } else if (nodo.dato.compareTo(dato) < 0) {
            if (nodo.der == null) {
                nodo.der = new NodoABB<>(dato);
                cantidad++;
            }else
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
        return nodo == null ? "" :
                listarAscendenteString(nodo.izq) +
                        (listarAscendenteString(nodo.izq).isEmpty() ? "" : "|") +
                        nodo.dato +
                        (listarAscendenteString(nodo.der).isEmpty() ? "" : "|" + listarAscendenteString(nodo.der));
    }
    public String listarDescendenteString() {
        return listarDescendenteString(raiz);
    }

    private String listarDescendenteString(NodoABB<T> nodo) {
        if (nodo == null) {
            return "";
        }

        String derecho = listarDescendenteString(nodo.der);
        String izquierdo = listarDescendenteString(nodo.izq);

        String resultado = (derecho.isEmpty() ? "" : derecho + "|") +
                nodo.dato +
                (izquierdo.isEmpty() ? "" : "|" + izquierdo);

        return resultado;
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
