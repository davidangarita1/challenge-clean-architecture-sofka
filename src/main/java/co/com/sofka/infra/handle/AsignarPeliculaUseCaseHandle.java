package co.com.sofka.infra.handle;

import co.com.sofka.domain.catalogo.command.AsignarPeliculaCommand;
import co.com.sofka.infra.generic.UseCaseHandle;
import co.com.sofka.useCases.ExtraerCatalogoUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AsignarPeliculaUseCaseHandle extends UseCaseHandle {

    private final ExtraerCatalogoUseCase extraerCatalogoUseCase;

    public AsignarPeliculaUseCaseHandle(ExtraerCatalogoUseCase extraerCatalogoUseCase) {
        this.extraerCatalogoUseCase = extraerCatalogoUseCase;
    }

    @ConsumeEvent(value = "sofka.asignarpelicula")
    void consumeBlocking(AsignarPeliculaCommand command) {
        var events = extraerCatalogoUseCase.apply(command);
        saveCatalogo(command.getCatalogoId(), events);
    }
}