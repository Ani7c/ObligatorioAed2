package dominio;

import Estructuras.ABB;

import java.util.Objects;

public class Equipo implements Comparable<Equipo>  {

    private String manager;
    private String nombre;
    private ABB<Jugador> jugadoresdelEquipo;

    public Equipo(String manager, String nombre) {
        this.manager = manager;
        this.nombre = nombre;
        this.jugadoresdelEquipo = new ABB<>();
    }
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getManager() {
        return manager;
    }

    public String getNombre() {
        return nombre;
    }
    public int getCantJugadoresdelEquipo() {
       return jugadoresdelEquipo.size();
    }

    @Override
    public String toString() {

        int cantidadJugadores = this.getCantJugadoresdelEquipo();
        return this.nombre + ";" + this.manager + ";" + cantidadJugadores ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre);
    }

    public ABB<Jugador> getJugadoresdelEquipo() {
        return jugadoresdelEquipo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.nombre);
    }
}
