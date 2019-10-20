import encapsulacion.Usuario;
import spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;
import spark.Session;

public class Main {

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
    }
}