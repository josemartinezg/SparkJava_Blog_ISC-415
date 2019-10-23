import dao.*;
import encapsulacion.Articulo;
import encapsulacion.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    private static Dao userDao;
    public static void main(String[] args) {
        userDao = new UsuarioDao();
        System.out.println("Prueba Dao");
        UsuarioDao usuarioDao = new UsuarioDao();
        ArticuloDao articuloDao = new ArticuloDao();
        ComentarioDao comentarioDao = new ComentarioDao();
        EtiquetaDao etiquetaDao = new EtiquetaDao();
        List<Usuario> allUsers = usuarioDao.getAll();
        Usuario chema = new Usuario("chema", "Jose", "ljf4656d", false, true);
        //userDao.save(chema);
        /*Inicio prueba con articulos...*/
        articuloDao.save(new Articulo("Why not to develop native Android", "As simple as this not cost effective and the learning curve is awful.",
                usuarioDao.get("jtmlmass").get(0), new Date()));
        articuloDao.save(new Articulo("Flutter is good, but not there yet",
                "The community still hasn't embraced it...",
                chema, new Date()));
       // System.out.println(articuloDao.getAll());
        //Cambiar el constructor y seguir probando....
        articuloDao.update(new Articulo("Flutter is good, but not there yet",
                "The documentation is stupid",
                chema, new Date()));
        articuloDao.delete(articuloDao.get("0").get(0));
        System.out.println(articuloDao.getAll());
        /*Fin de prueba con articulos...*/
        for(Usuario estudiante : allUsers){
            System.out.printf("Username: %s - Nombre: %s\n",
                    estudiante.getUsername(), estudiante.getNombre());
        }
        System.out.println(usuarioDao.getAll());
        Usuario selectedUser= usuarioDao.get("chema").get(0);
        System.out.println(selectedUser);
        //Update works
        userDao.update(new Usuario(selectedUser.getUsername(), selectedUser.getNombre(), "plepla",
                selectedUser.isAdministrator(),
                true));
        System.out.println(usuarioDao.getAll());
        userDao.delete(selectedUser);
        userDao.delete(selectedUser);
        System.out.println(usuarioDao.getAll());


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
