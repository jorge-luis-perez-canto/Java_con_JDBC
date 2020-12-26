package leccion3.manejoTransacciones.test;

import leccion3.manejoTransacciones.datos.Conexion;
import leccion3.manejoTransacciones.datos.UsuarioJDBC;
import leccion3.manejoTransacciones.domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jorge Luis Pérez Canto
 * @date 24/12/2020 02:10
 */

public class ManejoUsuarios {
    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            // Importante. Trasladar la conexion para poder utilizar el rollback en caso sea necesario.
            UsuarioJDBC usuarioJDBC = new UsuarioJDBC(conexion);

            // Primera transacción
            Usuario usuario1 = new Usuario();
            usuario1.setUsername("george");
            usuario1.setPassword("33466082");
            usuarioJDBC.insert(usuario1);

            // Segunda transacción
            Usuario usuario2 = new Usuario();
            usuario2.setId_usuario(4);
            usuario2.setUsername("george.crackerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            usuario2.setPassword("33466082");
            usuarioJDBC.update(usuario2);

            //Commit
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entrando al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
