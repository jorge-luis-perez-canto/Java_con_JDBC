package leccion1.introduccionJDBC;

import java.sql.*;

/**
 * @author Jorge Luis Pérez Canto
 * @date 23/12/2020 01:30
 */

public class IntroduccionJDBC {
    public static void main(String[] args) {
// Paso 1: Creamos nuestra cadena de conexión a MySql
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";

        try {

            // Paso 2: Creamos el objeto de conexion a la base de datos
            Connection conexion = DriverManager.getConnection(url, "root", "");

            // Paso 3: Creamos un objeto Statement
            Statement instruccion = conexion.createStatement();

            // Paso 4: Creamos el query
            String sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";

            // Paso 5. Ejecución el query
            ResultSet resultado = instruccion.executeQuery(sql);

            // Paso 6. Procesamos el resultado
            while (resultado.next()) {
                System.out.print("Id Persona: " + resultado.getInt(1));
                System.out.print(" Nombre: " + resultado.getString(2));
                System.out.print(" Apellido: " + resultado.getString(3));
                System.out.print(" Email: " + resultado.getString(4));
                System.out.println(" Teléfono: " + resultado.getString(5));
            }
            // Paso 7. Cerramos cada objeto que hemos utilizado
            resultado.close();
            instruccion.close();
            conexion.close();

        } catch (SQLException ex) {
            //Logger.getLogger(IntroduccionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.out);
        }
    }
}
