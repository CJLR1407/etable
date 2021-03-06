package etable.infrastructure.permiso.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import etable.domain.permiso.model.Permiso;
import etable.domain.permiso.repository.PermisoRepository;
import etable.web.constants.querys.Query;

@Component
public class PermisoRepositoryImpl implements PermisoRepository {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Autowired
	private PermisoRowMapper row;

	@Override
	public List<Permiso> getPermisos() {
		String query = "SELECT * FROM " + Query.table_permisos;
		List<Permiso> permisos = null;
		List<Map<String, Object>> rows = this.jdbctemplate.queryForList(query);
		permisos = this.row.mapRowPermisos(rows);
		return permisos;
	}

	@Override
	public List<Permiso> getPermisosBySubItem(int csubitem) {
		String query = Query.selectFromWhere(Query.table_permisos, "CSUBITEM", csubitem);
		List<Map<String, Object>> rows = this.jdbctemplate.queryForList(query);
		List<Permiso> permisos = this.row.mapRowPermisos(rows);
		return permisos;
	}

	@Override
	public Permiso editPermisoById(Permiso permiso) {
		String query = "UPDATE " + Query.table_permisos + " SET PERMISO = ? , DESCRIPCION = ? , ESTADO = ? WHERE CPERMISO = ? ";
		int update = this.jdbctemplate.update(query, permiso.getPermiso(), permiso.getDescripcion(),
				permiso.isEstado()? 1 : 0, permiso.getCpermiso());

		if (update == 1) {
			return permiso;
		}

		return null;
	}

	@Override
	public Permiso getPermisoById(int cpermiso) {
		String query = Query.selectFromWhere(Query.table_permisos, "CPERMISO", cpermiso);
		List<Map<String, Object>> rows = this.jdbctemplate.queryForList(query);
		List<Permiso> permisos = this.row.mapRowPermisos(rows);
		if(permisos != null) {
			if(permisos.size() > 0) {
				Permiso permiso = permisos.get(0);
				return permiso;
			}
		}
		return null;
	}

}
