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
        //indicando los recursos publicos.
        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("publico");
        //staticFiles.location("");
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        Usuario admin = new Usuario("admin", "Jose", "admin", true, true);

        before("/home",(request, response) -> {
            Usuario usuario=request.session(true).attribute("usuario");
            if(usuario==null){
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
            Session session=request.session(true);
            attributes.put("usuario", session.attribute("usuario"));
            return new ModelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

        Spark.post("/hacerLogin/", (request, response)->{
            //
            Session session=request.session(true);
            //
            Usuario usuario= null;//FakeServices.getInstancia().autenticarUsuario(request.params("usuario"), request.params("contrasena"));
            if(request.queryParams("username").equalsIgnoreCase(admin.getUsername()) && request.queryParams("password").equals(admin.getPassword())){
                //Buscar el usuario en la base de datos..
                usuario = new Usuario(admin.getUsername(), admin.getNombre(), admin.getPassword(), admin.isAdministrator(), admin.isAuthor());
            }else{
                response.redirect("/login");
            }

            session.attribute("usuario", usuario);
            //redireccionado a la otra URL.
            response.redirect("/home");

            return "";
        });

        Spark.get("/hacerLogout", (request, response)->{
            //creando cookie en para un minuto
            Session session=request.session();
            session.invalidate();
            response.redirect("/login");
            return "";
        });

        Spark.get("/", (request, response)->{
            Usuario usuario=request.session(true).attribute("usuario");
            if(usuario==null){
                response.redirect("/login");
            }else{
                response.redirect("/home");
            }
            return "";
        });

        /*userDao = new UsuarioDao();
        System.out.println("Prueba Dao");
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> allEstudiante = usuarioDao.getAll();
        for(Usuario estudiante : allEstudiante){
            System.out.printf("Username: %s - Nombre: %s\n",
                    estudiante.getUsername(), estudiante.getNombre());
        }
        //usuarioDao.save(new Usuario("jtmlmasxs", "Tomasss", "ljf4656d", false, true));
        System.out.println(usuarioDao.getAll());
        Usuario user= usuarioDao.get("chema").get(0);
        System.out.println(user);
        //Update works
        userDao.update(new Usuario(user.getUsername(), user.getNombre(), "plepla", user.isAdministrator(),
                true));
        System.out.println(usuarioDao.getAll());*/


//        Usuario user1 = getUser(0);
//        System.out.println(user1);
//        userDao.update(user1, new String[]{"Jake", "jake@domain.com"});

        //Usuario user2 = getUser(1);
        //userDao.delete(user2);
        //userDao.save(new Usuario("Julie", "julie@domain.com"));

        // userDao.getAll().forEach(user -> System.out.println(user.getName()));
    }

   /* private static Usuario getUser(String id) {
        List<Usuario> user = userDao.get(id);

        return user.orElseGet(
                () ->new Usuario("non-existing user", "no-email", "thisPass",
                        false, false));
    }*/
}