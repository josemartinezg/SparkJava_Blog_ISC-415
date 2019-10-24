import dao.*;
import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    private static Dao userDao;
    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDao();
        ArticuloDao articuloDao = new ArticuloDao();
        ComentarioDao comentarioDao = new ComentarioDao();
        EtiquetaDao etiquetaDao = new EtiquetaDao();

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

}

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
