package co.com.sofka.infra.entrypoint;

import co.com.sofka.domain.catalogo.command.AsignarPeliculaCommand;
import co.com.sofka.domain.catalogo.command.CrearCatalogoCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/asignarPelicula")
    public Response executor(AsignarPeliculaCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/crearCatalogo")
    public Response executor(CrearCatalogoCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }


}
