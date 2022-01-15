package co.com.sofka.domain.catalogo.event;

import co.com.sofka.domain.generic.DomainEvent;

public class CatalogoCreado extends DomainEvent {
    private String nombre;

    public CatalogoCreado(String nombre) {
        super("sofka.catalogocreado");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
