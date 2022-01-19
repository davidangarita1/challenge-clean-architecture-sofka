package co.com.sofka.useCases;

import co.com.sofka.domain.catalogo.Catalogo;
import co.com.sofka.domain.catalogo.command.AsignarPeliculaCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Dependent
public class ExtraerCatalogoUseCase implements Function<AsignarPeliculaCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;
    final String baseURL = "https://pelisplus.so/estrenos";

    public ExtraerCatalogoUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AsignarPeliculaCommand asignarPeliculaCommand) {

        var catalogo = Catalogo.from(asignarPeliculaCommand.getCatalogoId(),
                repository.getEventsBy("catalogo", asignarPeliculaCommand.getCatalogoId()));

        var document = urlBase();
        for (Element row : document.select(".items-peliculas .item-pelicula a")) {
            final String urlPelicula = row.attr("href");
            
            try {
                final Document movie = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();

                String nombre = movie.select(".info-content h1").text();
                String genero = movie.select(".info-content p:nth-of-type(4) span:nth-of-type(2)").text();
                String sinopsis = movie.select(".sinopsis").text();
                String fecha = movie.select(".info-content p:nth-of-type(2) span:nth-of-type(2)").text();
                String url = movie.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");

                catalogo.asignarPelicula(url,nombre,genero,sinopsis,fecha);

            } catch (Exception ex) {
                throw new ExtractCatalogoException();
            }
        }
        return catalogo.getUncommittedChanges();
    }

    public Document urlBase(){
        try {
            return Jsoup.connect(baseURL).get();
        } catch (IOException e) {
            throw new ExtractCatalogoException();
        }
    }
}
