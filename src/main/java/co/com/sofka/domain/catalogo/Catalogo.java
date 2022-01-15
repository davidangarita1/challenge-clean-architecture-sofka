package co.com.sofka.domain.catalogo;

import co.com.sofka.domain.catalogo.event.CatalogoCreado;
import co.com.sofka.domain.catalogo.event.PeliculaAsignada;
import co.com.sofka.domain.generic.AggregateRoot;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogo extends AggregateRoot {

    protected Map<String, Pelicula> peliculas;
    protected String nombre;

    public Catalogo(String catalogoId, String nombre){
        super(catalogoId);
        subscribe(new CatalogoEventChange(this));
        peliculas = new HashMap<>();
        appendChange(new CatalogoCreado(nombre)).apply();
    }

    private Catalogo(String id){
        super(id);
        subscribe(new CatalogoEventChange(this));
    }

    public void asignarPelicula(String id, String url, String nombre, String genero, String sinopsis, String fecha){
        appendChange(new PeliculaAsignada(url,nombre, genero, sinopsis, fecha)).apply();
    }


    public static Catalogo from(String id, List<DomainEvent> events){
        var catalogo = new Catalogo(id);
        events.forEach(catalogo::applyEvent);
        return catalogo;
    }

    public Map<String, Pelicula> peliculas() {
        return peliculas;
    }

    public String nombre() {
        return nombre;
    }
}
