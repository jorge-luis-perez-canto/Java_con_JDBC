package leccion5.manejoPoolConexiones.test;

import leccion5.manejoPoolConexiones.datos.Conexion;
import leccion5.manejoPoolConexiones.datos.PersonaDao;
import leccion5.manejoPoolConexiones.datos.PersonaDaoJDBC;
import leccion5.manejoPoolConexiones.domain.PersonaDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jorge Luis Pérez Canto
 * @date 23/12/2020 19:03
 */

public class ManejoPersonas {
    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaDao personaDao = new PersonaDaoJDBC(conexion);
            // -------------------------------------------

            List<PersonaDTO> personas = personaDao.select();

            for (PersonaDTO persona : personas) {
                System.out.println("Persona DTO: " + persona);
            }

            // -------------------------------------------
            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entrando al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }
}
