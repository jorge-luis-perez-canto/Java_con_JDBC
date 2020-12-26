package leccion5.manejoPoolConexiones.test;

import leccion5.manejoPoolConexiones.datos.Conexion;
import leccion5.manejoPoolConexiones.datos.UsuarioDaoJDBC;
import leccion5.manejoPoolConexiones.domain.UsuarioDTO;
import leccion5.manejoPoolConexiones.datos.UsuarioDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
            UsuarioDao usuarioDao = new UsuarioDaoJDBC(conexion);
            // -------------------------------------------

            List<UsuarioDTO> usuarios = usuarioDao.select();

            for (UsuarioDTO usuario : usuarios) {
                System.out.println(usuario);
            }

            // -------------------------------------------
            //Commit
            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");
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
