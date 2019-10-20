package dao;

import encapsulacion.Articulo;
import encapsulacion.Usuario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class ArticuloDao implements Dao<Articulo> {
    private Sql2o sql2o;
    private List<Articulo> misArticulos = new ArrayList<>();
    public ArticuloDao(){
        //subiendola en modo Embedded
        this.sql2o = new Sql2o("jdbc:h2:~/demosql2o2", "sa", "");
        createTable();
        //cargaDemo();
    }

    @Override
    public void createTable() {
        String sql="CREATE TABLE IF NOT EXISTS articulo(id integer PRIMARY KEY AUTO_INCREMENT NOT NULL, titulo VARCHAR(255)," +
                "cuerpo LONGTEXT NOT NULL, author BOOL NOT NULL, fecha DATETIME NOT NULL, " +
                "foreign key (author) refrences usuario (username);";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public List<Articulo> get(String id) {
        String sql="select * from articulo where id = :id;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Articulo.class);
        }
    }

    @Override
    public List<Articulo> getAll() {
        String sql="select * from articulo";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Articulo.class);
        }
    }

    @Override
    public void save(Articulo articulo) {
        String insertSql =
                "insert into usuario(titulo, cuerpo, autor, fecha) " +
                        "values (:titulo, :cuerpo, :autor, :fecha)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("titulo", articulo.getTitulo())
                    .addParameter("cuerpo", articulo.getCuerpo())
                    .addParameter("autor", articulo.getAutor().getUsername())
                    .addParameter("fecha", articulo.getFecha())
                    .executeUpdate();
        }
        //misUsuarios.add(usuario);
    }

    @Override
    public void update(Articulo articulo) {
        String updateSql = "UPDATE articulo SET titulo = :titulo, cuerpo = :cuerpo, autor = :autor, " +
                "fecha = :fecha where id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("titulo", articulo.getTitulo())
                    .addParameter("cuerpo", articulo.getCuerpo())
                    .addParameter("autor", articulo.getAutor().getUsername())
                    .addParameter("fecha", articulo.getFecha())
                    .addParameter("id", articulo.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Articulo articulo) {
        String updateSql = "DELETE FROM articulo WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("id", articulo.getId())
                    .executeUpdate();
        }
    }
}
