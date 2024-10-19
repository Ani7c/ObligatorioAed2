package interfaz;

import java.util.Arrays;
import java.util.Objects;

public enum Categoriadd {
    PRINCIPIANTE(0, "Principiante"),
    ESTANDARD(1, "Estándar"),
    PROFESIONAL(2, "Profesional");

    private final int indice;
    private final String texto;

    Categoriadd(int indice, String texto) {
        this.indice = indice;
        this.texto = texto;
    }

    public int getIndice() {
        return indice;
    }

    public String getTexto() {
        return texto;
    }

    public static Categoriadd fromTexto(String texto) {
        return Arrays.stream(Categoriadd.values())
                .filter(a -> Objects.equals(a.texto, texto))
                .findFirst()
                .orElse(null);
    }

}
