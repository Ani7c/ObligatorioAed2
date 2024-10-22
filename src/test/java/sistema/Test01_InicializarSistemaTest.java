package sistema;

import dominio.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_InicializarSistemaTest {
    Retorno retorno;


    @Test
    void noDeberiaInicializarSistemaConMaxSucursalesMenorOIgualA3() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(3);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(2);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void deberiaInicializarSistema() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void RegistraryBuscarJugador() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno= sistema.registrarJugador("jose77", "Jose", "Artigas", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno= sistema.registrarJugador("jose77", "Jose", "Artigas", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno= sistema.registrarJugador("jose77", "", "Artigas", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno= sistema.registrarJugador("jose77", "Jose", "Artigas", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno= sistema.buscarJugador("jose77");
        assertEquals("jose77;Jose;Artigas;PROFESIONAL",retorno.getValorString());
        assertEquals(1,retorno.getValorInteger());

        retorno= sistema.buscarJugador("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno= sistema.buscarJugador("pepepe");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    @Test
    void RegistrarEquipo() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno= sistema.registrarEquipo("Nacional", "Lasarte");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno= sistema.registrarEquipo("Aston Birra", "Felipe");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno= sistema.registrarEquipo("Aston Birra", "Felipe");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

@Test
void RegistrarJugadorEnEquipo() {
    Sistema sistema = new ImplementacionSistema();

    retorno = sistema.inicializarSistema(4);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    //creo los equipos
    retorno= sistema.registrarEquipo("Nacional", "Lasarte");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarEquipo("Defensor", "Peluso");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarEquipo("Aston Birra", "Felipe");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarEquipo("Aston Birra", "Felipe");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    //creo los jugadores
    retorno= sistema.registrarJugador("Leitik", "Lea", "Varela", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("Igor2", "Igor", "Mujica", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("Nicobolso", "Nico", "Hermida", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("Doc", "Vero", "Nica", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("Capa", "Andy", "Bandirashi", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("ElSexto", "Homero", "Simpson", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("Otro8", "Jorge", "Rodriguez", Categoria.PROFESIONAL);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.registrarJugador("Rookie", "mom", "jervaz", Categoria.PRINCIPIANTE);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("","Leitik");
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("Noexiste","Leitik");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("Aston Birra","Noexiste");
    assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("Nacional","Rookie");
    assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    //agrego 5 jugadores a un mismo equipo
    retorno = sistema.agregarJugadorAEquipo("Aston Birra","Leitik");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno = sistema.agregarJugadorAEquipo("Aston Birra","Igor2");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno = sistema.agregarJugadorAEquipo("Aston Birra","Nicobolso");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno = sistema.agregarJugadorAEquipo("Aston Birra","Doc");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno = sistema.agregarJugadorAEquipo("Aston Birra","Capa");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    //Error 4 por agregar 6to jugador
    retorno = sistema.agregarJugadorAEquipo("Aston Birra","ElSexto");
    assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("Nacional","Otro8");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("Nacional","Otro8");
    assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());

    retorno= sistema.agregarJugadorAEquipo("Defensor","Otro8");
    assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());



}
@Test
    void ListarJugadoresDeEquipo() {
        Sistema sistema = new ImplementacionSistema();

        // Inicializamos el sistema
        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Creamos equipos
        retorno = sistema.registrarEquipo("Liverpool", "Klopp");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("Manchester City", "Guardiola");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Registramos jugadores profesionales con apellidos inventados y apodos en el alias
        retorno = sistema.registrarJugador("MoSalah", "Mohamed", "ElSherif", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("SaMane", "Sadio", "Diop", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("BobbyFirmino", "Roberto", "Silva", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("BigVirgil", "Virgil", "Jones", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("AliBecker", "Alisson", "Rodriguez", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        //jugadores mancity
        retorno = sistema.registrarJugador("KevinDeBruyne", "Kevin", "De Bruyne", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("RubenDias", "Ruben", "Dias", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        //agregar jugadores al mancity
         retorno = sistema.agregarJugadorAEquipo("Manchester City", "KevinDeBruyne");
         assertEquals(Retorno.Resultado.OK, retorno.getResultado());

         retorno = sistema.agregarJugadorAEquipo("Manchester City", "RubenDias");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Agregamos jugadores al equipo Liverpool
        retorno = sistema.agregarJugadorAEquipo("Liverpool", "MoSalah");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.agregarJugadorAEquipo("Liverpool", "SaMane");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.agregarJugadorAEquipo("Liverpool", "BobbyFirmino");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.agregarJugadorAEquipo("Liverpool", "BigVirgil");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


        // Listamos jugadores de Liverpool en orden por alias
        retorno = sistema.listarJugadoresDeEquipo("Liverpool");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Verificamos si el listado está ordenado correctamente por alias y con el formato esperado
        String jugadoresEsperados = "BigVirgil;Virgil;Jones;PROFESIONAL|BobbyFirmino;Roberto;Silva;PROFESIONAL|MoSalah;Mohamed;ElSherif;PROFESIONAL|SaMane;Sadio;Diop;PROFESIONAL";
        assertEquals(jugadoresEsperados, retorno.getValorString());

        // Listar jugadores de Mancity
        retorno = sistema.listarJugadoresDeEquipo("Manchester City");
         assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Resultado esperado
        String resultadoEsperado = "KevinDeBruyne;Kevin;De Bruyne;PROFESIONAL|RubenDias;Ruben;Dias;PROFESIONAL";
        assertEquals(resultadoEsperado, retorno.getValorString());

        // Intentamos listar jugadores de un equipo que no existe
        retorno = sistema.listarJugadoresDeEquipo("Barcelona");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        // Intentamos listar jugadores con nombre de equipo vacío
        retorno = sistema.listarJugadoresDeEquipo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());


    }


}
