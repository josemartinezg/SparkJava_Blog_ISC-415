import dao.*;
import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;

import java.sql.SQLException;
import java.sql.Date;
import encapsulacion.Usuario;
import org.h2.engine.User;
import servicios.ArticuloServices;
import servicios.DataBaseServices;
import servicios.InicioServices;
import servicios.UsuarioServices;
import spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import dao.Dao;
import dao.UsuarioDao;

import java.util.List;
import java.util.Optional;

import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;
import spark.Session;

public class Main {
    private static Dao userDao;

    public static void main(String[] args) throws SQLException {
        InicioServices.iniciarDb();
        DataBaseServices.getInstance().testConexion();
        InicioServices.crearTablas();

        ArticuloServices articuloServices = new ArticuloServices();
        UsuarioServices usuarioServices = new UsuarioServices();

        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("/publico");
        //staticFiles.location("");

        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        before("*", (request, response) -> {
            Session session = request.session(true);
            if(session.attribute("usuario") == null)
                session.attribute("usuario", "");
        });

        Spark.get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        Spark.get("/register", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "register.ftl");
        }, freeMarkerEngine);

        Spark.get("/home", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            List<Articulo> articulos = articuloServices.selectArticulos();
            attributes.put("articulos", articulos);
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

        Spark.get("/articulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);

        Spark.get("/articulo/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String idArticulo = request.params("id");
            Articulo articulo = articuloServices.getArticulo(Integer.parseInt(idArticulo));
            attributes.put("articulo", articulo);
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);
        //Obtener al usuario específico.
        Spark.get("/author/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String username = request.params("id");
            Usuario author = usuarioServices.getUsuario(username);
            attributes.put("author", author);
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "author.ftl");
        }, freeMarkerEngine);
    //Mostrar el usuario que inició la sesión
        Spark.get("/author", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "author.ftl");
        }, freeMarkerEngine);

        Spark.post("/hacerLogin/", (request, response) -> {
            Session session = request.session(true);
            List<Usuario> usuarios = usuarioServices.getAllUsuarios();
            Usuario usuario = null;
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            for (Usuario u : usuarios){
                System.out.println(u.toString());
                if (username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())) {
                    usuario = new Usuario(u.getUsername(), u.getNombre(), u.getPassword(), u.isAdministrator(), u.isAuthor());
                }
            }

            session.attribute("usuario", usuario);
            //redireccionado a la otra URL.
            response.redirect("/home");

            return "";
        });

        Spark.post("/hacerRegister/", (request, response) -> {
            String nombre = request.queryParams("name");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            boolean isadmin = false;
            boolean isauthor = false;
            String auxIsAdmin = request.queryParams("isadmin");
            String auxIsAuthor = request.queryParams("isauthor");
            if(auxIsAdmin == null){
                isadmin = false;
            }else if(auxIsAdmin.equals("on")){
                isadmin = true;
            }
            if(auxIsAuthor == null){
                isauthor = false;
            }else if(auxIsAuthor.equals("on")){
                isauthor = true;
            }
            System.out.println(request.queryParams("isauthor"));
            Usuario usuario = new Usuario(username, nombre, password, isadmin, isauthor);
            usuarioServices.crearUsuario(usuario);

            //redireccionado a la otra URL.
            response.redirect("/login");

            return "";
        });

        Spark.get("/hacerLogout", (request, response) -> {
            //creando cookie en para un minuto
            Session session = request.session();
            session.invalidate();
            response.redirect("/login");
            return "";
        });

        Spark.get("/", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null) {
                response.redirect("/login");
            } else {
                response.redirect("/home");
            }
            return "";
        });
    }
}

