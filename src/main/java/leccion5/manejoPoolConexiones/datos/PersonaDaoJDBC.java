package leccion5.manejoPoolConexiones.datos;

import leccion5.manejoPoolConexiones.domain.PersonaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Jorge Luis Pérez Canto
 * @date 23/12/2020 18:31
 */

public class PersonaDaoJDBC implements PersonaDao {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";

    public PersonaDaoJDBC() {
    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<PersonaDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO personaDTO = null;

        List<PersonaDTO> personaDTOS = new ArrayList<PersonaDTO>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            System.out.println("\nMostrando registros...");
            while (rs.next()) {
                // Recuperar cada columna
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                // Crear un objeto de tipo Persona
                personaDTO = new PersonaDTO();
                personaDTO.setId_persona(id_persona);
                personaDTO.setNombre(nombre);
                personaDTO.setApellido(apellido);
                personaDTO.setEmail(email);
                personaDTO.setTelefono(telefono);

                // Agregar a la lista de objetos de tipo persona
                personaDTOS.add(personaDTO);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return personaDTOS;
    }

    public int insert(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("\nEjecutando query: " + SQL_INSERT);
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, personaDTO.getNombre());
            stmt.setString(2, personaDTO.getApellido());
            stmt.setString(3, personaDTO.getEmail());
            stmt.setString(4, personaDTO.getTelefono());
            System.out.println(stmt.toString());
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int update(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("\nEjecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, personaDTO.getNombre());
            stmt.setString(2, personaDTO.getApellido());
            stmt.setString(3, personaDTO.getEmail());
            stmt.setString(4, personaDTO.getTelefono());
            stmt.setInt(5, personaDTO.getId_persona());
            System.out.println(stmt.toString());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int delete(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("\nEjecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, personaDTO.getId_persona());
            System.out.println(stmt.toString());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
            Conexion.close(conn);
        }
        return rows;
    }
}
