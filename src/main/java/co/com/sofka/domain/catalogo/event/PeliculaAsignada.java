package co.com.sofka.domain.catalogo.event;

import co.com.sofka.domain.generic.DomainEvent;

public class PeliculaAsignada extends DomainEvent {

    private final String peliculaId;
    private final String url;
    private final String nombre;
    private final String genero;
    private final String sinopsis;
    private final String fecha;

    public PeliculaAsignada(String peliculaId, String url, String nombre, String genero, String sinopsis, String fecha) {
        super("sofka.peliculaasignada");
        this.peliculaId = peliculaId;
        this.url = url;
        this.nombre = nombre;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.fecha = fecha;
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getFecha() {
        return fecha;
    }
}
