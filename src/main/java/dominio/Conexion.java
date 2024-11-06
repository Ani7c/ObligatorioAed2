package dominio;

public class Conexion {
    private int latencia;
    private boolean existe;

    public Conexion() {
        this.existe = false;
    }

    public Conexion(int latencia) {
        this.latencia = latencia;
        this.existe = true;
    }

    public Conexion(boolean existe, int latencia) {
        this.latencia = latencia;
        this.existe = existe;
    }

    public Conexion(Conexion c) {
        this.latencia = c.getLatencia();
        this.existe = c.isExiste();
    }
    public int getLatencia() {

        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public String toString() {
        return "Conexion{" +
                "latencia=" + latencia +
                ", existe=" + existe +
                '}';
    }
}
