package co.com.sofka.domain.catalogo;

import java.util.Objects;

public class Pelicula {
    private final String id;
    private final String url;
    private final String nombre;
    private final String genero;
    private final String sinopsis;
    private final String fecha;

    public Pelicula(String id, String url, String nombre, String genero, String sinopsis, String fecha) {
        this.id = Objects.requireNonNull(id);
        this.url = Objects.requireNonNull(url);
        this.nombre = Objects.requireNonNull(nombre);
        this.genero = Objects.requireNonNull(genero);
        this.sinopsis = Objects.requireNonNull(sinopsis);
        this.fecha = Objects.requireNonNull(fecha);
    }

    public String id() {
        return id;
    }

    public String url() {
        return url;
    }

    public String nombre() {
        return nombre;
    }

    public String genero() {
        return genero;
    }

    public String sinopsis() {
        return sinopsis;
    }

    public String fecha() {
        return fecha;
    }
}
