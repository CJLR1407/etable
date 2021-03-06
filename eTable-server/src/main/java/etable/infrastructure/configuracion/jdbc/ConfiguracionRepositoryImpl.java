package etable.infrastructure.configuracion.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import etable.domain.configuracion.model.Configuracion;
import etable.domain.configuracion.model.Image;
import etable.domain.configuracion.repository.ConfiguracionRepository;
import etable.web.constants.querys.Query;

@Component
public class ConfiguracionRepositoryImpl implements ConfiguracionRepository {

	private final JdbcTemplate jdbcTemplate;
	private final ConfiguracionRowMapper row;
	
	public ConfiguracionRepositoryImpl(JdbcTemplate jdbcTemplate, ConfiguracionRowMapper row) {
		this.jdbcTemplate = jdbcTemplate;
		this.row = row;
	}
	
	@Override
	public Configuracion getConfiguracion() {
		String query = "SELECT * FROM " + Query.table_configuracion;
		Configuracion configuracion = null;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		configuracion = row.mapRowConfigSistema(rows);
		return configuracion;
	}

	@Override
	public Configuracion actualizarById(Configuracion configuracion, Image image) {
		String pathName = image.saveImage();
		configuracion.setEmplogo(pathName);
		String query = Query.update_configuracion;
		int update = this.jdbcTemplate.update(query, configuracion.getCempresa(), 
						configuracion.getEmpnombre(), configuracion.getEmpdireccion(),
						configuracion.getEmpdescripcion(), configuracion.getEmpemail(),
						configuracion.getEmpcelular(), configuracion.getEmplogo(),
						configuracion.isSist_reservacion_cliente() ? 1 : 0,
						configuracion.isSist_atencion_cliente() ? 1 : 0,
						configuracion.isMesas_compuestas() ? 1 : 0,
						configuracion.isAgregar_cliente_manual() ? 1 : 0,
						configuracion.isPagos_tarjeta_credito() ? 1 : 0,
						configuracion.isReservacion_pedidos() ? 1 : 0,
						configuracion.isReservas_especiales() ? 1 : 0,
						configuracion.isReservas_no_sesionadas() ? 1 : 0,
						configuracion.getDate_configurado(),
						configuracion.getCconfiguracion());
		
		if(update == 1) {
			return configuracion;
		}
		
		return null;
	}
	
	@Override
	public Configuracion actualizarParametrosById(Configuracion configuracion) {
		String query = Query.update_parametros;
		int update = this.jdbcTemplate.update(query, configuracion.getCant_max_mesas(), configuracion.getCant_max_us_registrados(), configuracion.getHorario_ini_atencion(), 
				configuracion.getHorario_fin_atencion(), configuracion.getDias_atencion(), configuracion.getMax_us_trab_conectados());
		if( update == 1) {
			return configuracion;
		} else {
			return null;
		}
	}

	@Override
	public Configuracion getParametrosGenerales() {
		String query = "SELECT * FROM " + Query.table_configuracion;
		Configuracion configuracion = null;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		configuracion = row.mapRowConfigParametros(rows);
		return configuracion;
	}
}
