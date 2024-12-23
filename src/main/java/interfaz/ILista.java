package interfaz;
import Estructuras.Lista;

import java.util.Iterator;
public interface ILista<T>{
    void insertar(T dato);
    void borrar(T dato);
    int largo();
    boolean existe(T dato);
    T recuperar(T dato);
    boolean esVacia();
    boolean esLlena();
    void imprimirDatos();
    Iterator<T> iterator();
    void insertarOrdenado(T dato);
    String toString();
    Lista.NodoLista<T> getInicio();

}
