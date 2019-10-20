package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import encapsulacion.Usuario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class UsuarioDao implements Dao<Usuario> {
    private Sql2o sql2o;
    private List<Usuario> misUsuarios = new ArrayList<>();
    public UsuarioDao(){
        //subiendola en modo Embedded
        this.sql2o = new Sql2o("jdbc:h2:~/demosql2o2", "sa", "");
        crearTabla();
        cargaDemo();
    }
    private void crearTabla(){
        String sql="CREATE TABLE IF NOT EXISTS usuario(username VARCHAR(124) PRIMARY KEY NOT NULL, nombre VARCHAR(100)," +
                "password VARCHAR(100) NOT NULL, administrator BOOL NOT NULL, author BOOL NOT NULL);";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public List<Usuario> get(String username) {
        String sql="select * from usuario where username = :username;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("username", username)
                    .executeAndFetch(Usuario.class);
        }
    }

    @Override
    public List<Usuario> getAll() {
        String sql="select * from usuario";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Usuario.class);
        }
    }

    @Override
    public void save(Usuario usuario) {
        String insertSql =
                "insert into usuario(username, nombre, password, administrator, author) " +
                        "values (:username, :nombre, :password, :administrator, :author)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("username", usuario.getUsername())
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("administrator", usuario.isAdministrator())
                    .addParameter("author", usuario.isAuthor())
                    .executeUpdate();
        }
        misUsuarios.add(usuario);
    }

    @Override
    public void update(Usuario usuario, String[] params) {

    }

    @Override
    public void delete(Usuario usuario) {

    }

        private void cargaDemo(){
            System.out.println("Cargando el demo...");
            if(getAll().isEmpty()){
                crearDataDemo();
            }
        }

    public void crearDataDemo(){
        String sql = "insert into usuario(username, nombre, password, administrator, author) values(:username,:nombre, " +
                ":password, :administrator, :author)";
        try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                    .addParameter("username", "jtmlmass")
                    .addParameter("nombre", "Jose ")
                    .addParameter("password", "asdfgh55555jkl")
                    .addParameter("administrator", false)
                    .addParameter("author", false)
                    .executeUpdate();
        }
    }
}
/*
*     /**
 *
 */





