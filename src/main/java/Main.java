import dao.*;
import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;

import java.sql.Array;
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

        Etiqueta auxEtiqueta = new Etiqueta();


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
            attributes.put("usuario", session.attribute("usuario").toString());
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);

        Spark.get("/crearArticulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "crearArticulo.ftl");
        }, freeMarkerEngine);

        Spark.post("/postArticulo/", (request, response) -> {
            Session session = request.session(true);
            String titulo = request.queryParams("titulo");
            String cuerpo = request.queryParams("cuerpo");
            String autor = session.attribute("usuario").toString();
            Date fecha = new Date(System.currentTimeMillis());
            Articulo articulo = new Articulo(titulo, cuerpo, autor, fecha);
            int idArt = articuloServices.insertArticulo(articulo);
            articuloServices.insertArticulo(articulo);
            String etiquetas = request.queryParams("etiquetas");
            String inputTags[] = etiquetas.split(",");
            ArrayList<Etiqueta> auxList = new ArrayList<>();
            for (String etiqueta: inputTags) {
                Etiqueta etiquetaAux = new Etiqueta();
                etiquetaAux.setEtiqueta(etiqueta);
                etiquetaAux.setArticulo(idArt);
                articuloServices.crearEtiqueta(etiquetaAux);
                auxList.add(etiquetaAux);
            }
            articulo.setListaEtiquetas(auxList);
            //misEstudiantes.add(estudiante);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "New Article");
            System.out.println(articulo.getListaEtiquetas().toString());
            attributes.put("articulo", articulo);
            response.redirect("/home");
            return null;
        }, freeMarkerEngine);

        Spark.get("/articulo/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            int idArticulo = Integer.valueOf(request.params("id"));
            Articulo articulo = articuloServices.getArticulo(idArticulo);
            System.out.println(articulo);
            ArrayList<Etiqueta> auxList = new ArrayList<>();
            ArrayList<Etiqueta> misEtiquetas;
            misEtiquetas = articuloServices.getAllEtiquetas();
            for (Etiqueta tag : misEtiquetas){
                if (tag.getArticulo() == idArticulo){
                    auxList.add(tag);
                }
            }
            articulo.setListaEtiquetas(auxList);
            attributes.put("articulo", articulo);
            System.out.println(articulo.getListaEtiquetas().toString());
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            System.out.println(articulo.getListaEtiquetas().toString());
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);
        //Obtener al usuario específico.
        Spark.get("/author/:username", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String username = request.params("username");
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
            if(session.attribute("usuario") == null || session.attribute("usuario") == ""){
                response.redirect("/login");
            }
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
            if(auxIsAdmin.equals("on")){
                isadmin = true;
            }
            if(auxIsAuthor.equals("on")){
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
            response.redirect("/home");
            return "";
        });

        Spark.get("/", (request, response) -> {
            response.redirect("/home");
            return "";
        });

        //Filtros
        before("*", (request, response) -> {
            Session session = request.session(true);
            if(session.attribute("usuario") == null)
                session.attribute("usuario", "");
        });

//        before("/crearArticulo",(request, response) -> {
//            Usuario usuario = request.session().attribute("usuario");
//            if(usuario==null){
//                response.redirect("/login");
//            }
//        });
    }
}

