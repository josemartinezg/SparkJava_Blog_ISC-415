package dao;

import encapsulacion.Etiqueta;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

public class EtiquetaDao implements Dao<Etiqueta> {
    private Sql2o sql2o;
   // private List<Usuario> misUsuarios = new ArrayList<>();
    public EtiquetaDao() {
        //subiendola en modo Embedded
        this.sql2o = new Sql2o("jdbc:h2:~/demosql2o2", "sa", "");
        createTable();
        //cargaDemo();
    }

    @Override
    public void createTable() {
        String sql="CREATE TABLE IF NOT EXISTS etiqueta(" +
                "id integer PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "articulo integer NOT NULL, " +
                "etiqueta VARCHAR(255) NOT NULL,);" +
                "ALTER TABLE etiqueta\n" +
                "    ADD FOREIGN KEY (articulo) \n" +
                "    REFERENCES articulo (id);";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public List<Etiqueta> get(String id) {
        String sql="select * from etiqueta where id = :id;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Etiqueta.class);
        }
    }

    @Override
    public List<Etiqueta> getAll() {
        String sql="select * from etiqueta";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Etiqueta.class);
        }
    }

    @Override
    public void save(Etiqueta etiqueta) {
        String insertSql =
                "insert into etiqueta(articulo, etiqueta) values (:articulo, :tag)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("articulo", etiqueta.getArticulo())
                    .addParameter("tag", etiqueta.getEtiqueta())
                    .executeUpdate();
        }
    }
//Revisar que semilla tiene de malo este query...
    @Override
    public void update(Etiqueta etiqueta) {
        String updateSql = "UPDATE etiqueta SET etiqueta = :etiqueta," +
                "articulo = :articulo" +
                "where id = :id;";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("articulo", etiqueta.getArticulo())
                    .addParameter("etiqueta", etiqueta.getEtiqueta())
                    .addParameter("id", etiqueta.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Etiqueta etiqueta) {
        String updateSql = "DELETE FROM etiqueta WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("id", etiqueta.getId())
                    .executeUpdate();
        }
    }
}
