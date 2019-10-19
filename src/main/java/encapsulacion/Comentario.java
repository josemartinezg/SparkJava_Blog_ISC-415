package encapsulacion;

public class Comentario {
    private long id;
    private String comentario;
    private Articulo articulo;
    private Usuario autor;

    public Comentario() {}
    
    public Comentario(long id, String comentario, Articulo articulo, Usuario autor) {
        this.id = id;
        this.comentario = comentario;
        this.articulo = articulo;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", articulo=" + articulo +
                ", autor=" + autor +
                '}';
    }
}
