package sistema;

import Estructuras.ABB;
import dominio.Equipo;
import dominio.Jugador;
import dominio.Categoria;
import interfaz.*;

public class ImplementacionSistema implements Sistema {


    private ABB<Equipo> ABBEquipo;
    private ABB<Jugador> ABBJugador;


    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= 3) {
            return Retorno.error1("La cantidad de sucursales debe ser mayor a 3");
        }
        ABBEquipo = new ABB<Equipo>();
        ABBJugador = new ABB<Jugador>();
        //inicializar las sucursales

        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {

        //    1. Si alguno de los parámetros es vacío o null.
        //  2. Si ya existe un jugador registrado con ese alias.
        Jugador nuevoJugador = new Jugador(alias, nombre, apellido, categoria);
        if (alias == "" || nombre == "" || apellido == "" || categoria == null) {
            return Retorno.error1("Los parametros no pueden ser vacios");
        }
        Jugador jugadorBuscado = ABBJugador.buscar(nuevoJugador);
        if (jugadorBuscado != null) {
            return Retorno.error2("Ya existe un jugador registrado con ese alias");
        }
        ABBJugador.insertar(nuevoJugador);
        return Retorno.ok();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        Jugador jugadorBuscado = ABBJugador.buscar(new Jugador(alias));
        if (jugadorBuscado != null) {
            StringBuilder resultado = new StringBuilder();
            resultado.append(jugadorBuscado.toString());
        }
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoriadd unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
