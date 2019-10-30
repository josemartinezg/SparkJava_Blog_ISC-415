package servicios;

import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticuloServices {
    /*
    *
    OPERACIONES ARTICULOS
    *
    * */
    /* Listar en orden (SELECT DESC) */
    public ArrayList<Articulo> selectArticulos() {
        ArrayList<Articulo> lista = new ArrayList<>();
        Connection con = null;
        try {
            //Trayendo los articulos en el orden requetido en la práctica.
            String query = "SELECT * FROM articulo ORDER BY fecha DESC";
            con = DataBaseServices.getInstance().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Articulo art = new Articulo();
                art.setId(rs.getInt("id"));
                art.setTitulo(rs.getString("titulo"));
                art.setCuerpo(rs.getString("cuerpo"));
                art.setFecha(rs.getDate("fecha"));
                art.setAutor(rs.getString("autor"));
                System.out.println(art.getTitulo());
                lista.add(art);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return lista;
    }
    /* Obtener artículo específico (SELECT WHERE ID) */
    public Articulo getArticulo(int codigoArticulo) {
        Articulo articulo = null;
        Connection con = null;

        try {
            String consultaSql = "SELECT * FROM articulo WHERE id = ?";
            con = DataBaseServices.getInstance().getConexion();

            PreparedStatement preparedStatement = con.prepareStatement(consultaSql);
            preparedStatement.setInt(1, codigoArticulo);
            ResultSet rs = preparedStatement.executeQuery();
            articulo = new Articulo();
            while (rs.next()) {
                articulo.setId(codigoArticulo);
                articulo.setTitulo(rs.getString("titulo"));
                articulo.setCuerpo(rs.getString("cuerpo"));
                articulo.setFecha(rs.getDate("fecha"));
                articulo.setAutor(rs.getString("autor"));
            }
            ArrayList<Etiqueta> misEtiquetas = new ArrayList<>();
            while (rs.next()) {
                Etiqueta tag = new Etiqueta();
                tag.setId(rs.getInt("id"));
                tag.setEtiqueta(rs.getString("nombre"));
                misEtiquetas.add(tag);
            }
            String consultaSqlComentario = "SELECT * FROM comentario WHERE articulo = ?";
            PreparedStatement preparedStatementComentario = con.prepareStatement(consultaSqlComentario);
            preparedStatementComentario.setInt(1, codigoArticulo);
            ResultSet rsComentario = preparedStatementComentario.executeQuery();
            ArrayList<Comentario> comentarios = new ArrayList<>();
            while(rsComentario.next()) {
                Comentario com = new Comentario();
                com.setArticulo(articulo);
                com.setAutor(rsComentario.getString("autor"));
                com.setId(rsComentario.getInt("id"));
                com.setComentario(rsComentario.getString("comentario"));
                comentarios.add(com);
            }
            articulo.setListaComentarios(comentarios);
            articulo.setListaEtiquetas(misEtiquetas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulo;
    }

    /* Crear un articulo (INSERT) */
    public int insertArticulo(Articulo articulo){
        boolean ok =false;
        int codigo = 0;

        Connection con = null;
        try {

            String query = "INSERT INTO ARTICULO(titulo, cuerpo, fecha, autor) values(?,?,?,?)";
            con = DataBaseServices.getInstance().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());
            prepareStatement.setDate(3, articulo.getFecha());
            prepareStatement.setString(4, articulo.getAutor());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

            if(ok) {
                String queryCodigo = "SELECT id FROM articulo WHERE titulo = ?";
                PreparedStatement preparedStatementParaCodigo = con.prepareStatement(queryCodigo);
                preparedStatementParaCodigo.setString(1, articulo.getTitulo());
                ResultSet rs = preparedStatementParaCodigo.executeQuery();
                while (rs.next()) {
                    codigo = rs.getInt("id");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return codigo;
    }
    /* Actualizar Articulos (UPDATE)*/
    public boolean updateArticulo(Articulo articulo){
        boolean ok =false;

        Connection con = null;
        try {
            String query = "UPDATE articulo SET titulo=?, cuerpo=?, fecha=?, AUTOR=? WHERE id = ?";
            con = DataBaseServices.getInstance().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());
            prepareStatement.setDate(3, articulo.getFecha());
            prepareStatement.setString(4, articulo.getAutor());
            prepareStatement.setLong(5, articulo.getId());
            //Indica el where...
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ok;
    }

    /* Borrar Artículo DELETE*/
    public boolean borrarArticulo(int idArticulo){
        boolean ok =false;

        Connection con = null;
        try {
            con = DataBaseServices.getInstance().getConexion();
            String queryComentarios = "DELETE FROM comentario WHERE articulo = ?";
            PreparedStatement preparedStatementComentarios = con.prepareStatement(queryComentarios);
            preparedStatementComentarios.setInt(1, idArticulo);
            preparedStatementComentarios.executeUpdate();

            String query = "DELETE FROM articulo WHERE id = ?";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setInt(1, idArticulo);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ok;
    }
    public boolean crearComentario(Comentario comentario, int articulo) {
        boolean ok =false;

        Connection connection = null;
        try {
            String query = "INSERT INTO comentario(comentario, articulo, autor) values(?,?,?)";
            connection = DataBaseServices.getInstance().getConexion();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, comentario.getComentario());
            prepareStatement.setInt(2, articulo);
            prepareStatement.setString(3, comentario.getAutor());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ok;
    }

    public boolean borrarComentario(int idComentario) {
        boolean ok = false;

        Connection con = null;

        try {
            con = DataBaseServices.getInstance().getConexion();
            String queryComentarios = "DELETE FROM comentario WHERE id = ?";
            PreparedStatement preparedStatementComentarios = con.prepareStatement(queryComentarios);
            preparedStatementComentarios.setInt(1, idComentario);
            int fila = preparedStatementComentarios.executeUpdate();
            ok = fila > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }
    public boolean crearEtiqueta(Etiqueta etiqueta) {
        boolean ok =false;

        Connection con = null;
        try {
            String query = "INSERT INTO ETIQUETA(nombre_etiqueta) values(?)";
            con = DataBaseServices.getInstance().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, etiqueta.getEtiqueta());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ok;

    }

}
