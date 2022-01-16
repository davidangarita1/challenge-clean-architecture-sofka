package co.com.sofka.useCases;

import co.com.sofka.domain.catalogo.command.AsignarPeliculaCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.EventStoreRepository;

import java.util.List;
import java.util.function.Function;

public class ExtraerCatalogoUseCase implements Function<AsignarPeliculaCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public ExtraerCatalogoUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AsignarPeliculaCommand asignarPeliculaCommand) {
        return null;
    }
}
