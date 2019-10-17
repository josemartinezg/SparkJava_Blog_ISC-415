package encapsulacion;

import java.util.ArrayList;
import java.util.Date;

public class Articulo {
    private long id;
    private String titulo;
    private String cuerpo;
    private Usuario autor;
    private Date fehca;
    private ArrayList<Comentario> listaComentarios;
    private ArrayList<Etiqueta> listaEtiquetas;
}
