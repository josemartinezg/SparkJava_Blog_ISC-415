import dao.Dao;
import dao.UsuarioDao;
import encapsulacion.Usuario;

import java.util.List;
import java.util.Optional;

public class Main {
    private static Dao userDao;
    public static void main(String[] args) {
        userDao = new UsuarioDao();
        System.out.println("Prueba Dao");
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> allEstudiante = usuarioDao.getAll();
        for(Usuario estudiante : allEstudiante){
            System.out.printf("Username: %s - Nombre: %s\n",
                    estudiante.getUsername(), estudiante.getNombre());
        }
        //usuarioDao.save(new Usuario("jtmlmasxs", "Tomasss", "ljf4656d", false, true));
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
