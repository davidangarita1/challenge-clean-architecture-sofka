package co.com.sofka.domain.catalogo;

import co.com.sofka.domain.catalogo.event.CatalogoCreado;
import co.com.sofka.domain.catalogo.event.PeliculaAsignada;
import co.com.sofka.domain.generic.EventChange;

import java.util.HashMap;

public class CatalogoEventChange implements EventChange {

    public CatalogoEventChange(Catalogo catalogo) {
        listener((CatalogoCreado event)-> {
            catalogo.nombre = event.getNombre();
            catalogo.peliculas = new HashMap<>();
        });

        listener((PeliculaAsignada event)->{
            var pelicula = new Pelicula(event.getPeliculaId(), event.getUrl(), event.getNombre(), event.getGenero(), event.getSinopsis(), event.getFecha());
            catalogo.peliculas.put(event.getPeliculaId(), pelicula);
        });
    }
}
