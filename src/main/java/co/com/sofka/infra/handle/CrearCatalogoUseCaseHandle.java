package co.com.sofka.infra.handle;

import co.com.sofka.domain.catalogo.command.CrearCatalogoCommand;
import co.com.sofka.infra.generic.UseCaseHandle;
import co.com.sofka.useCases.CrearCatalogoUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CrearCatalogoUseCaseHandle  extends UseCaseHandle {

    private final CrearCatalogoUseCase crearCatalogoUseCase;

    public CrearCatalogoUseCaseHandle(CrearCatalogoUseCase crearCatalogoUseCase) {
        this.crearCatalogoUseCase = crearCatalogoUseCase;
    }

    @ConsumeEvent(value = "sofka.crearcatalogo")
    void consumeBlocking(CrearCatalogoCommand command) {
        var events = crearCatalogoUseCase.apply(command);
        saveCatalogo(command.getCatalogoId(), events);
    }
}