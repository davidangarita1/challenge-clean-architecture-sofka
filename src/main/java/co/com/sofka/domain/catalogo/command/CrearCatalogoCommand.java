package co.com.sofka.domain.catalogo.command;

public class CrearCatalogoCommand {

    private String catalogoId;
    private String nombre;

    public CrearCatalogoCommand(){
    }

    public String getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
