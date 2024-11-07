package dominio;

import java.util.Objects;

public class Sucursal implements Comparable<Sucursal> {

    private String codigo;
    private String nombre;

    public Sucursal(){

    }

    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }


    public Sucursal(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }

    @Override
    public int compareTo(Sucursal o) {
        return this.codigo.compareTo(o.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return Objects.equals(codigo, sucursal.codigo);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
