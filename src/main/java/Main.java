import dao.*;
import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;
import java.util.Date;
import encapsulacion.Usuario;
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

    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDao();
        ArticuloDao articuloDao = new ArticuloDao();
        ComentarioDao comentarioDao = new ComentarioDao();
        EtiquetaDao etiquetaDao = new EtiquetaDao();
//        etiquetaDao.save(new Etiqueta("Desarrollo Movil Nativo"));
//        articuloDao.save(new Articulo("This is new...", "As simple as this not cost effective and the learning curve is awful.",
//               "chema", new Date(), 3));
//        articuloDao.save(new Articulo("This is new...", "As simple as this not cost effective and the learning curve is awful.",
//                "chema", new Date(), 3));
//        articuloDao.save(new Articulo("This is new...", "As simple as this not cost effective and the learning curve is awful.",
//                "chema", new Date(), 3));
        //indicando los recursos publicos.
        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("/publico");
        //staticFiles.location("");
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        Usuario admin = new Usuario("admin", "Jose", "admin", true, true);

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
            List<Articulo> articulos = articuloDao.getAll();
            UsuarioDao userDao = new UsuarioDao();
            attributes.put("user", userDao.get("chema").get(0).getNombre());
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
            Articulo articulo = articuloDao.get(idArticulo).get(0);
            attributes.put("articulo", articulo);
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);
        //Obtener al usuario específico.
        Spark.get("/author/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            //String username = request.params("username");
            String username2 = request.params("id");
            Usuario author = usuarioDao.get(username2).get(0);
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
            List<Usuario> usuarios = usuarioDao.getAll();
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
            usuarioDao.save(usuario);

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
//        userDao = new UsuarioDao();
//        System.out.println("Prueba Dao");
//        UsuarioDao usuarioDao = new UsuarioDao();
//        List<Usuario> allEstudiante = usuarioDao.getAll();
//        for(Usuario estudiante : allEstudiante){
//            System.out.printf("Username: %s - Nombre: %s\n",
//                    estudiante.getUsername(), estudiante.getNombre());

//        usuarioDao.save(new Usuario("jtmlmass", "Tomas", "ljf4656d", false, true));
//        System.out.println(usuarioDao.getAll());
//        Usuario user= usuarioDao.get("chema").get(0);
//        System.out.println(user);

        //Update works
//        userDao.update(new Usuario(selectedUser.getUsername(), selectedUser.getNombre(), "plepla",
//                selectedUser.isAdministrator(),
//                true));
//        System.out.println(usuarioDao.getAll());
//        userDao.delete(selectedUser);
//        userDao.delete(selectedUser);
//        System.out.println(usuarioDao.getAll());

        /* Articulo Works
         */

//        articuloDao.save(new Articulo("This is new...", "As simple as this not cost effective and the learning curve is awful.",
//                usuarioDao.get("chema").get(0).getUsername(), new Date()));
//        articuloDao.update(new Articulo(6, "Flutter is good, but not there yet",
//                "The documentation is stupid",
//                usuarioDao.get("chema").get(0).getUsername(), new Date()));
//        System.out.println(articuloDao.getAll());
//        System.out.println(articuloDao.get("6"));
//Funciona to... respira tu dema....

        /* Comentario Works
         */

//        comentarioDao.save(new Comentario("This is bullshit, Flutter rocks.", 6, "jtmlmass"));
//        comentarioDao.save(new Comentario("Agreed. Flutter rocks", 6, "chema"));
//        comentarioDao.update(new Comentario(1, "Pretty fucking horrid this flutter thing is!.", 6, "jtmlmass"));
//        System.out.println(articuloDao.getAll());
//        System.out.println(articuloDao.get("1"));

//        etiquetaDao.save(new Etiqueta(1, "Desarrollo Movil Nativo"));
//        etiquetaDao.save(new Etiqueta(3, "Opinion"));
//        /*etiquetaDao.update(new Etiqueta(1, "Desarrollo Movil Hibrido"));*/
//        System.out.println(etiquetaDao.getAll());
//        System.out.println(etiquetaDao.get("1"));




//        Usuario user1 = getUser(0);
//        System.out.println(user1);
//        userDao.update(user1, new String[]{"Jake", "jake@domain.com"});

    //Usuario user2 = getUser(1);
    //userDao.delete(user2);
    //userDao.save(new Usuario("Julie", "julie@domain.com"));

        // userDao.getAll().forEach(user -> System.out.println(user.getName()));

   /* private static Usuario getUser(String id) {
        List<Usuario> user = userDao.get(id);

        return user.orElseGet(
                () ->new Usuario("non-existing user", "no-email", "thisPass",
                        false, false));
    }*/