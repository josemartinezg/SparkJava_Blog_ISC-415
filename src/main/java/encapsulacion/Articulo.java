package encapsulacion;

import java.util.ArrayList;
import java.util.Date;

public class Articulo {
    private long id;
    private String titulo;
    private String cuerpo;
    private String autor;
    private int etiqueta;
    private Date fecha;

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }


    public Articulo() {
    }
    public Articulo(String titulo, String cuerpo, String autor, Date fecha, int etiqueta) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
    }
    public Articulo(long id, String titulo, String cuerpo, String autor, Date fecha, int etiqueta) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
//
//    public ArrayList<Comentario> getListaComentarios() {
//        return listaComentarios;
//    }
//
//    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
//        this.listaComentarios = listaComentarios;
//    }
//
//    public ArrayList<Etiqueta> getListaEtiquetas() {
//        return listaEtiquetas;
//    }
//
//    public void setListaEtiquetas(ArrayList<Etiqueta> listaEtiquetas) {
//        this.listaEtiquetas = listaEtiquetas;
//    }

//    private ArrayList<Comentario> listaComentarios;
//    private ArrayList<Etiqueta> listaEtiquetas;


    public Date getFehca() {
        return fecha;
    }

    public void setFehca(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", autor=" + autor +
                ", fehca=" + fecha +
//                ", listaComentarios=" + listaComentarios +
//                ", listaEtiquetas=" + listaEtiquetas +

                '}';
    }
}
