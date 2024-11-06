package Estructuras;

import interfaz.ICola;
import dominio.Conexion;
import dominio.Sucursal;
import interfaz.ILista;


public class Grafo {
    private Sucursal[] sucursales;
    private Conexion[][] conexiones;
    private final int maxSucursales;
    private final boolean dirigido;
    private int cantDeVertices;

    public Grafo(int cantMaxDeVertices, boolean esDirigido) {
        maxSucursales = cantMaxDeVertices;
        dirigido = esDirigido;
        sucursales = new Sucursal[maxSucursales];
        conexiones = new Conexion[maxSucursales][maxSucursales];

        if (dirigido) {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = 0; j < conexiones.length; j++) {
                    conexiones[i][j] = new Conexion();
                }
            }
        } else {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = 0; j < conexiones.length; j++) {
                    Conexion a = new Conexion();
                    conexiones[i][j] = a;
                    conexiones[j][i] = a;
                }
            }
        }
    }

    public int getCantDeVertices() {
        return cantDeVertices;
    }

    public void agregarSucursal(Sucursal suc) {
        if (cantDeVertices < maxSucursales) {
            int posLibre = obtenerPosLibre();
            sucursales[posLibre] = suc;
            cantDeVertices++;
        }
    }
    public void borrarSucursal(Sucursal suc) {
        int posABorrar = obtenerPos(suc);
        for (int i = 0; i < conexiones.length; i++) {
            conexiones[posABorrar][i].setExiste(false); //Fila: Conexiones adyacentes
            if (dirigido) {
                conexiones[i][posABorrar].setExiste(false);//Columna: Conexiones incidentes
            }
        }
        sucursales[posABorrar] = null;
        cantDeVertices--;
    }
    public void agregarConexion(Sucursal sInicial, Sucursal sFinal, Conexion conexion) {
        int posSInicial = obtenerPos(sInicial);
        int posSFinal = obtenerPos(sFinal);

        Conexion aux = conexiones[posSInicial][posSFinal];
        aux.setLatencia(conexion.getLatencia());
        aux.setExiste(true);
    }


    public void borrarConexion(Sucursal sInicial, Sucursal sFinal) {
        int posSInicial = obtenerPos(sInicial);
        int posSFinal = obtenerPos(sFinal);

        Conexion aux = conexiones[posSInicial][posSFinal];
        aux.setLatencia(0);
        aux.setExiste(false);
    }
    public Conexion obtenerConexion(Sucursal sInicial, Sucursal sFinal) {
        int posSInicial = obtenerPos(sInicial);
        int posSFinal = obtenerPos(sFinal);

        return conexiones[posSInicial][posSFinal];
    }

    public boolean existeSucursal(Sucursal suc) {
        int posABuscar = obtenerPos(suc);
        return posABuscar >= 0;
    }

    public ILista<Sucursal> adyacentes(Sucursal vert) {
        ILista<Sucursal> adyacentes = new Lista<>();
        int pos = obtenerPos(vert);

        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[pos][i].isExiste()) {
                adyacentes.insertar(sucursales[i]);
            }
        }
        return adyacentes;
    }

    //Recorridas

    public boolean[] dfs(Sucursal suc, Conexion[][] con) {
        boolean[] visitados = new boolean[maxSucursales];
        int posV = obtenerPos(suc);
        if (posV != -1) {
            dfs(posV, visitados, con);
        }
        return visitados;
    }

    private void dfs(int posV, boolean[] visitados, Conexion[][] conexiones) {
        visitados[posV] = true;

        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[posV][i].isExiste() && !visitados[i]) {
                dfs(i, visitados, conexiones);
            }
        }

    }
    public Lista<Sucursal> bfs(Sucursal suc) {
        int posV = obtenerPos(suc);
        boolean[] visitados = new boolean[maxSucursales];
        Lista<Sucursal> listaSucursales = new Lista<>(); // Lista para almacenar las sucursales visitadas
        visitados[posV] = true;
        ICola<Integer> cola = new Cola<>();
        cola.encolar(posV);

        while (!cola.estaVacia()) {
            int posDesencolada = cola.desencolar();
            listaSucursales.insertar(sucursales[posDesencolada]); // Agregar la sucursal a la lista
            for (int i = 0; i < sucursales.length; i++) {
                if (conexiones[posDesencolada][i].isExiste() && !visitados[i]) {
                    visitados[i] = true;
                    cola.encolar(i);
                }
            }
        }
        return listaSucursales; // Retornar la lista de sucursales visitadas
    }
    public boolean esPuntoCritico(Sucursal suc){
        int posV = obtenerPos(suc);
        boolean[] visitadosOrigial = this.dfs(suc,conexiones);
        Conexion[][] copiacon = new Conexion[maxSucursales][maxSucursales];
        // Copiar las conexiones de la matriz original
        for (int i = 0; i < maxSucursales; i++) {
            for (int j = 0; j < maxSucursales; j++) {
                // Crear una nueva conexión con los mismos atributos que la conexión original
                copiacon[i][j] = new Conexion(conexiones[i][j].isExiste(), conexiones[i][j].getLatencia());
            }
        }
        for (int i = 0; i < maxSucursales; i++) {
            copiacon[posV][i].setExiste(false); // Quita conexiones desde el vértice
            copiacon[i][posV].setExiste(false); // Quita conexiones hacia el vértice
        }
        int newPos = -1;
        for(int i = 0; i < visitadosOrigial.length; i++){
            if(i!=posV){
                if(visitadosOrigial[i]){
                    newPos = i;
                    break;
                }

            }
        }
        if (newPos == -1){
            return true;
        }
        Sucursal sucNueva = sucursales[newPos];
        boolean[] visitadosNuevos = dfs(sucNueva,copiacon);
        // Comparar los arreglos de visitados, ignorando la posición posV
        for (int i = 0; i < visitadosOrigial.length; i++) {
            if (i != posV && visitadosOrigial[i] != visitadosNuevos[i]) {
                return true; // Si hay diferencia en visitados, es un punto crítico
            }
        }
        return false;
    }


    private int obtenerPosLibre() {
        for (int i = 0; i < sucursales.length; i++) {
            if (sucursales[i] == null) {
                return i;
            }
        }
        return -1;
    }


    public int obtenerPos(Sucursal suc) {
        for (int i = 0; i < conexiones.length; i++) {
            if (sucursales[i] != null && sucursales[i].equals(suc)) {
                return i;
            }
        }
        return -1;
    }

}




