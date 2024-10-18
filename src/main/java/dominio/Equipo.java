package dominio;

import java.util.Objects;

public class Equipo implements Comparable<Equipo>  {

    private String manager;
    private String nombre;

    public Equipo(String manager, String nombre) {
        this.manager = manager;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public int compareTo(Equipo o) {
        return o.nombre.compareTo(this.nombre); // Cambiamos el orden para decreciente
    }
}
