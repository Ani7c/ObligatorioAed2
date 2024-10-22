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
    void RegistrarJugador() {
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

}
