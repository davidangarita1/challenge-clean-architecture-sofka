package co.com.sofka.infra.materialize;

import co.com.sofka.domain.catalogo.event.CatalogoCreado;
import co.com.sofka.domain.catalogo.event.PeliculaAsignada;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CatalogoHandle {
    private final MongoClient mongoClient;

    public CatalogoHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofka.catalogocreado", blocking = true)
    void consumeCatalogoCreado(CatalogoCreado event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("nombre", event.getNombre());

        mongoClient.getDatabase("queries")
                .getCollection("catalogo")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofka.peliculaasignada", blocking = true)
    void consumePeliculaAsignada(PeliculaAsignada event) {
        BasicDBObject document = new BasicDBObject();
        document.put("pelicula."+event.getNombre()+".nombre", event.getNombre());
        document.put("pelicula."+event.getNombre()+".fecha", event.getFecha());
        document.put("pelicula."+event.getNombre()+".sinopsis", event.getSinopsis());
        document.put("pelicula."+event.getNombre()+".genero", event.getGenero());
        document.put("pelicula."+event.getNombre()+".url", event.getUrl());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("catalogo")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }
}