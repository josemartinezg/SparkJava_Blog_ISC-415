package encapsulacion;

import java.util.ArrayList;
import java.util.Date;

public class Articulo {
    private long id;
    private String titulo;
    private String cuerpo;
    private Usuario autor;
    private Date fecha;

    public Articulo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public ArrayList<Etiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(ArrayList<Etiqueta> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }

    private ArrayList<Comentario> listaComentarios;
    private ArrayList<Etiqueta> listaEtiquetas;

    public Articulo(long id, String titulo, String cuerpo, Usuario autor, Date fehca, ArrayList<Comentario> listaComentarios, ArrayList<Etiqueta> listaEtiquetas) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fehca = fehca;
        this.listaComentarios = listaComentarios;
        this.listaEtiquetas = listaEtiquetas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFehca() {
        return fehca;
    }

    public void setFehca(Date fehca) {
        this.fehca = fehca;
    }

    public ArrayList<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public ArrayList<Etiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", autor=" + autor +
                ", fehca=" + fehca +
                ", listaComentarios=" + listaComentarios +
                ", listaEtiquetas=" + listaEtiquetas +
                '}';
    }
}
