package leccion3.manejoTransacciones.test;

import leccion3.manejoTransacciones.datos.Conexion;
import leccion3.manejoTransacciones.datos.PersonaJDBC;
import leccion3.manejoTransacciones.domain.Persona;

import java.sql.*;

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

            PersonaJDBC personaJDBC = new PersonaJDBC(conexion);

            // -------------------------------------------
            // Primera transacción -> update
            Persona cambioPersona = new Persona();
            cambioPersona.setId_persona(4);
            cambioPersona.setNombre("María Ivonne");
            cambioPersona.setApellido("Galindo");
            cambioPersona.setEmail("mgalindo@mail.com");
            cambioPersona.setTelefono("56879468");

            personaJDBC.update(cambioPersona);

            // -------------------------------------------
            // Segunda transacción -> insert

            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Carlos");
            //nuevaPersona.setApellido("Ramirezzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            nuevaPersona.setApellido("Ramirez");
            personaJDBC.insert(nuevaPersona);

            // -------------------------------------------

            conexion.commit();

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
