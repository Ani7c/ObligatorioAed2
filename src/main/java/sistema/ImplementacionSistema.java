package sistema;

import Estructuras.ABB;
import Estructuras.Grafo;
import Estructuras.Lista;
import dominio.Conexion;
import dominio.Equipo;
import dominio.Jugador;
import dominio.Sucursal;
import interfaz.*;

public class ImplementacionSistema implements Sistema {


    private ABB<Equipo> ABBEquipo;
    private ABB<Jugador> ABBJugador;
    private Grafo Sucursales;
    private int maxSucursales;
    private ABB<Jugador> AbbPrincipiante;
    private ABB<Jugador> AbbEstandard;
    private ABB<Jugador> AbbProfesional;


    @Override
    public Retorno inicializarSistema(int maxSucursal) {
        if (maxSucursal <= 3) {
            return Retorno.error1("La cantidad de sucursales debe ser mayor a 3");
        }
        ABBEquipo = new ABB<Equipo>();
        ABBJugador = new ABB<Jugador>();
        Sucursales = new Grafo(maxSucursal, false);
        maxSucursales = maxSucursal;

        AbbPrincipiante = new ABB<>();
        AbbEstandard = new ABB<>();
        AbbProfesional = new ABB<>();

        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {

        //    1. Si alguno de los parámetros es vacío o null.
        //  2. Si ya existe un jugador registrado con ese alias.
        Jugador nuevoJugador = new Jugador(alias);
        if (alias == "" || nombre == "" || apellido == "" || categoria == null || alias == null || nombre == null || apellido == null ) {
            return Retorno.error1("Los parametros no pueden ser vacios");
        }
        Jugador jugadorBuscado = ABBJugador.buscar(nuevoJugador);
        if (jugadorBuscado != null) {
            return Retorno.error2("Ya existe un jugador registrado con ese alias");
        }
        nuevoJugador.setCategoria(categoria);
        nuevoJugador.setApellido(apellido);
        nuevoJugador.setNombre(nombre);
        ABBJugador.insertar(nuevoJugador);
        switch (categoria) {
            case PRINCIPIANTE:
                AbbPrincipiante.insertar(nuevoJugador);
                break;
            case ESTANDARD:
                AbbEstandard.insertar(nuevoJugador);
                break;
            case PROFESIONAL:
                AbbProfesional.insertar(nuevoJugador);
                break;
        }
        return Retorno.ok();

    }

    @Override
    public Retorno buscarJugador(String alias) {
        if (alias == null || alias.isEmpty()) {
            return Retorno.error1("Los parametros no pueden ser vacios o null");
        }
        Jugador jugadorBuscado = new Jugador(alias);
        Retorno ret = ABBJugador.buscarconretorno(jugadorBuscado);
        if (ret.getValorString() == null) {
            return Retorno.error2("No existe un jugador con ese alias");
        }
        return Retorno.ok(ret.getValorInteger(), ret.getValorString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        String resultado = ABBJugador.listarAscendenteString();
        return Retorno.ok(resultado);
    }


    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        ABB<Jugador> AbbCategoria;

        switch (unaCategoria) {
            case PRINCIPIANTE:
                AbbCategoria = AbbPrincipiante;
                break;
            case ESTANDARD:
                AbbCategoria = AbbEstandard;
                break;
            case PROFESIONAL:
                AbbCategoria = AbbProfesional;
                break;
            default:
                return Retorno.error1("Categoría inválida");
        }

        String resultado = AbbCategoria.listarAscendenteString();
        return Retorno.ok(resultado);
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        //    1. Si alguno de los parámetros es vacío o null.
        //  2. Si ya existe un Equipo registrado con ese nombre.
        if (nombre == "" || manager == "" || nombre == null || manager == null) {
            return Retorno.error1("Los parametros no pueden ser vacios");
        }
        Equipo nuevoEquipo = new Equipo(manager, nombre);
        Equipo equipoBuscado = ABBEquipo.buscar(nuevoEquipo);
        if (equipoBuscado != null) {
            return Retorno.error2("Ya existe un equipo registrado con ese nombre");
        }
        ABBEquipo.insertar(nuevoEquipo);
        return Retorno.ok();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if (nombreEquipo == "" || aliasJugador == "" || aliasJugador == null || nombreEquipo == null) {
            return Retorno.error1("Los parametros no pueden ser vacios o nullos");
        }
        Equipo equipo = new Equipo(nombreEquipo);
        Equipo equipoBuscado = ABBEquipo.buscar(equipo);
        if (equipoBuscado == null) {
            return Retorno.error2("No existe equipo con este nombre");
        }
        Jugador jugador = new Jugador(aliasJugador);
        Jugador jugadorBuscado = ABBJugador.buscar(jugador);
        if (jugadorBuscado == null) {
            return Retorno.error3("No existe un jugador con ese alias");
        }
        if (equipoBuscado.getCantJugadoresdelEquipo() >= 5) {
            return Retorno.error4("el equipo ya tiene 5 integrantes");
        }
        if (jugadorBuscado.getCategoria() != Categoria.PROFESIONAL) {
            return Retorno.error5("El jugador no es profesional");
        }
        if (jugadorBuscado.getEquipoActual() != null) {
            return Retorno.error6("El jugador ya existe un equipo");
        }
        equipoBuscado.getJugadoresdelEquipo().insertar(jugadorBuscado);
        jugadorBuscado.setEquipoActual(equipo);
        return Retorno.ok();

    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.isEmpty()) {
            return Retorno.error1("Los parametros no pueden ser vacios o nullos");
        }
        Equipo equipo = new Equipo(nombreEquipo);
        Equipo equipoBuscado = ABBEquipo.buscar(equipo);
        if (equipoBuscado == null) {
            return Retorno.error2("No existe equipo con este nombre");
        }
        String jugadoresListados = equipoBuscado.getJugadoresdelEquipo().listarAscendenteString();
        System.out.println(jugadoresListados);
        return Retorno.ok(jugadoresListados);
    }

    @Override
    public Retorno listarEquiposDescendente() {
        String EquiposListado = ABBEquipo.listarDescendenteString();
        System.out.println(EquiposListado);
        return Retorno.ok(EquiposListado);
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        if (Sucursales.getCantDeVertices() >= maxSucursales) {
            return Retorno.error1("No se pueden registrar mas sucursales");
        }
        if (codigo == null || nombre == null || codigo.isEmpty()  || nombre.isEmpty()) {
            return Retorno.error2("Los parametros no pueden ser vacios o nullos");
        }
        Sucursal sucursal = new Sucursal(codigo, nombre);
        int existe = Sucursales.obtenerPos(sucursal);
        if (existe > -1) {
            return Retorno.error3("ya existe una sucursal con ese código");
        }

        Sucursales.agregarSucursal(sucursal);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia < 0) {
            return Retorno.error1("Latencia debe ser mayor a 0");
        }
        if (codigoSucursal1 == null || codigoSucursal2 == null || codigoSucursal1 == "" || codigoSucursal2 == "") {
            return Retorno.error2("Los parametros no pueden ser vacios o nulos");
        }
        Sucursal sucursal1 = new Sucursal(codigoSucursal1);
        Sucursal sucursal2 = new Sucursal(codigoSucursal2);
        int existe1 = Sucursales.obtenerPos(sucursal1);
        int existe2 = Sucursales.obtenerPos(sucursal2);
        if (existe1 < 0 || existe2 < 0) {
            return Retorno.error3("Sucursal inexistente");
        }
        if (Sucursales.obtenerConexion(sucursal1, sucursal2).isExiste()) {
            return Retorno.error4("Ya existe una conexión entre las dos sucursales");
        }
        Conexion conexion = new Conexion(latencia);
        Sucursales.agregarConexion(sucursal1, sucursal2, conexion);


        return Retorno.ok();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0){
            return Retorno.error1("Latencia debe ser mayor a 0");
        }
        if(codigoSucursal1 == null || codigoSucursal2 == null || codigoSucursal1 == "" || codigoSucursal2 == "") {
            return Retorno.error2("Los parametros no pueden ser vacios o nulos");
        }
        Sucursal sucursal1 = new Sucursal(codigoSucursal1);
        Sucursal sucursal2 = new Sucursal(codigoSucursal2);
        int existe1 = Sucursales.obtenerPos(sucursal1);
        int existe2 = Sucursales.obtenerPos(sucursal2);
        if(existe1 < 0 || existe2 < 0) {
            return Retorno.error3("Sucursal inexistente");
        }
        Conexion conexion = Sucursales.obtenerConexion(sucursal1, sucursal2);
        if(!conexion.isExiste()){
            return Retorno.error4("No existe conexion entre las 2 sucursales");
        }
        conexion.setLatencia(latencia);


        return Retorno.ok();
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
