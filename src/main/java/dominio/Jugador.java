package dominio;

import java.util.Objects;

public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private String apellido;
    private String alias;
    private Categoria categoria;
    private Equipo equipoActual;

    public Jugador(String nombre, String apellido, String alias, Categoria categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.categoria = categoria;
        this.equipoActual = null;
    }

    public Jugador(String alias) {
        this.alias = alias;
    }

    public Equipo getEquipoActual() {
        return equipoActual;
    }

    public void setEquipoActual(Equipo equipoActual) {
        this.equipoActual = equipoActual;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getAlias() {
        return alias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(alias, jugador.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(alias);
    }

    @Override
    public String toString() {
        return alias + ";" + nombre + ";" + apellido + ";" + categoria;
    }

    @Override
    public int compareTo(Jugador o) {
        return this.alias.compareTo(o.alias);
    }
}
