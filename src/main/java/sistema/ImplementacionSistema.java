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
        if(maxSucursales <= 3) { return Retorno.error1("La cantidad de sucursales debe ser mayor a 3");}
        ABBEquipo = new ABB<Equipo>();
        ABBJugador = new ABB<Jugador>();
        //inicializar las sucursales

        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {

    //    1. Si alguno de los parámetros es vacío o null.
      //  2. Si ya existe un jugador registrado con ese alias.
        Jugador nuevoJugador = new Jugador(alias);
        if(alias == "" || nombre == "" || apellido == "" || categoria == null) {
            return Retorno.error1("Los parametros no pueden ser vacios");
        }
        Jugador jugadorBuscado = ABBJugador.buscar(nuevoJugador);
        if(jugadorBuscado != null) {
            return Retorno.error2("Ya existe un jugador registrado con ese alias");
        }

            nuevoJugador.setCategoria(categoria);
            nuevoJugador.setApellido(apellido);
            nuevoJugador.setNombre(nombre);
            ABBJugador.insertar(nuevoJugador);
        return Retorno.ok();

        }



    @Override
    public Retorno buscarJugador(String alias) {
        if(alias == "" || alias == null) {
            return Retorno.error1("Los parametros no pueden ser vacios o null");
        }
        Jugador jugadorBuscado = new Jugador(alias);
        Retorno ret = ABBJugador.buscarconretorno(jugadorBuscado);
        if(ret.getValorString()==null) {
            return Retorno.error2("No existe un jugador con ese alias");
        }
        return Retorno.ok(ret.getValorInteger(), ret.getValorString());
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
        //    1. Si alguno de los parámetros es vacío o null.
        //  2. Si ya existe un Equipo registrado con ese nombre.
        if(nombre == "" || manager == "") {
            return Retorno.error1("Los parametros no pueden ser vacios");
        }
        Equipo nuevoEquipo = new Equipo(manager,nombre);
        Equipo equipoBuscado = ABBEquipo.buscar(nuevoEquipo);
        if(equipoBuscado != null) {
            return Retorno.error2("Ya existe un equipo registrado con ese nombre");
        }
        ABBEquipo.insertar(nuevoEquipo);
        return Retorno.ok();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if(nombreEquipo == "" || aliasJugador == ""|| aliasJugador == null||nombreEquipo == null) {
            return Retorno.error1("Los parametros no pueden ser vacios o nullos");
        }
        Equipo equipo = new Equipo(nombreEquipo);
        Equipo equipoBuscado = ABBEquipo.buscar(equipo);
        if(equipoBuscado == null) {
            return Retorno.error2("No existe equipo con este nombre");
        }
        Jugador jugador = new Jugador(aliasJugador);
        Jugador jugadorBuscado = ABBJugador.buscar(jugador);
        if(jugadorBuscado == null) {
            return Retorno.error3("No existe un jugador con ese alias");
        }
        if(equipoBuscado.getCantJugadoresdelEquipo() >= 5 ){
            return Retorno.error4("el equipo ya tiene 5 integrantes");
        }
        if(jugadorBuscado.getCategoria()!= Categoria.PROFESIONAL){
            return Retorno.error5("El jugador no es profesional");
        }
        if(jugadorBuscado.getEquipoActual()!= null){
            return Retorno.error6("El jugador ya existe un equipo");
        }
        equipoBuscado.getJugadoresdelEquipo().insertar(jugadorBuscado);
        jugadorBuscado.setEquipoActual(equipo);
        return Retorno.ok();

    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if(nombreEquipo=="" || nombreEquipo == null) {
            return Retorno.error1("Los parametros no pueden ser vacios o nullos");
        }
        Equipo equipo = new Equipo(nombreEquipo);
        Equipo equipoBuscado = ABBEquipo.buscar(equipo);
        if(equipoBuscado == null) {
            return Retorno.error2("No existe equipo con este nombre");
        }
        String jugadoresListados = equipoBuscado.getJugadoresdelEquipo().listarAscendenteString();
        System.out.println(jugadoresListados);
        return Retorno.ok(jugadoresListados);
    }

    @Override
    public Retorno listarEquiposDescendente() {
        String EquiposListado = ABBEquipo.listarDescendenteString();
        return Retorno.ok(EquiposListado);
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
