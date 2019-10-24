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

        //indicando los recursos publicos.
        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("publico");
        //staticFiles.location("");
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        Usuario admin = new Usuario("admin", "Jose", "admin", true, true);

        before("/home", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null) {
                //parada del request, enviando un codigo.
                response.redirect("/login");
            }
        });

        Spark.get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        Spark.get("/home", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

        Spark.get("/post", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);

        Spark.get("/author", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "author.ftl");
        }, freeMarkerEngine);

        Spark.post("/hacerLogin/", (request, response) -> {
            //
            Session session = request.session(true);
            //
            Usuario usuario = null;//FakeServices.getInstancia().autenticarUsuario(request.params("usuario"), request.params("contrasena"));
            if (request.queryParams("username").equalsIgnoreCase(admin.getUsername()) && request.queryParams("password").equals(admin.getPassword())) {
                //Buscar el usuario en la base de datos..
                usuario = new Usuario(admin.getUsername(), admin.getNombre(), admin.getPassword(), admin.isAdministrator(), admin.isAuthor());
            } else {
                response.redirect("/login");
            }

            session.attribute("usuario", usuario);
            //redireccionado a la otra URL.
            response.redirect("/home");

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