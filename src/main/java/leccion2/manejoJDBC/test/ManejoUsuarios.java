package leccion2.manejoJDBC.test;

import leccion2.manejoJDBC.datos.UsuarioJDBC;
import leccion2.manejoJDBC.domain.Usuario;

import java.util.List;

/**
 * @author Jorge Luis Pérez Canto
 * @date 24/12/2020 02:10
 */

public class ManejoUsuarios {
    public static void main(String[] args) {
        UsuarioJDBC usuarioJDBC= new UsuarioJDBC();

        //Ejecutando el listado de usuarios
        List<Usuario> usuarios = usuarioJDBC.select();

        for (Usuario usuario : usuarios) {
            System.out.println("Usuario: "+ usuario);
        }

        //Insertamos un nuevo registro
//        Usuario usuario = new Usuario("carlos.juerez", "1234");
//        usuarioJDBC.insert(usuario);

        //Modificamos un usuario existente
//        Usuario usuario = new Usuario(3, "carlos.juarez", "456");
//        usuarioJDBC.update(usuario);

        //Eliminación
//        usuarioJDBC.delete(new Usuario(3));

    }
}
