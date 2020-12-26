package leccion5.manejoPoolConexiones.datos;

import leccion5.manejoPoolConexiones.domain.PersonaDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jorge Luis Pérez Canto
 * @date 24/12/2020 18:06
 */

public interface PersonaDao {

    public List<PersonaDTO> select() throws SQLException;
    public int insert(PersonaDTO persona) throws SQLException;
    public int update(PersonaDTO persona) throws SQLException;
    public int delete(PersonaDTO persona) throws SQLException;

}
