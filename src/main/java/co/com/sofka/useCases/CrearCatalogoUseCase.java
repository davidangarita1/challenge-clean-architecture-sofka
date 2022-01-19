package co.com.sofka.useCases;

import co.com.sofka.domain.catalogo.Catalogo;
import co.com.sofka.domain.catalogo.command.CrearCatalogoCommand;
import co.com.sofka.domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CrearCatalogoUseCase implements Function<CrearCatalogoCommand, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(CrearCatalogoCommand command) {
        var catalogo = new Catalogo(command.getCatalogoId(), command.getNombre());
        return catalogo.getUncommittedChanges();
    }
}
