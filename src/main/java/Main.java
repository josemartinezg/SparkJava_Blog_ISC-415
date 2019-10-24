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

        etiquetaDao.save(new Etiqueta("Desarrollo Movil Nativo"));
        etiquetaDao.save(new Etiqueta("Opinion"));
      //  etiquetaDao.update(new Etiqueta(1, "Desarrollo Movil Hibrido"));
        System.out.println(etiquetaDao.getAll());
        System.out.println(etiquetaDao.get("1"));

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
