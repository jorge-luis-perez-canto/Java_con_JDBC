package leccion5.manejoPoolConexiones.datos;

import leccion5.manejoPoolConexiones.domain.UsuarioDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jorge Luis Pérez Canto
 * @date 24/12/2020 20:38
 */

public interface UsuarioDao {
    public List<UsuarioDTO> select() throws SQLException;
    public int insert(UsuarioDTO usuario) throws SQLException;
    public int update(UsuarioDTO usuario) throws SQLException;
    public int delete(UsuarioDTO usuario) throws SQLException;

}
