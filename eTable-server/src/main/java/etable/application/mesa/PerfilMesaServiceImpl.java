package etable.application.mesa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etable.domain.mesa.model.PerfilMesa;
import etable.domain.mesa.repository.PerfilMesaRepository;

@Service
public class PerfilMesaServiceImpl implements PerfilMesaService{

	@Autowired
	private PerfilMesaRepository repository;


	@Override
	public List<PerfilMesa> getPerfilMesas() {
		// TODO Auto-generated method stub
		return this.repository.getPerfilMesas();
	}

	@Override
	public PerfilMesa actualizarPerfilMesaById(PerfilMesa perfilmesa, int id) {
		// TODO Auto-generated method stub
		return this.repository.actualizarPerfilMesaById(perfilmesa, id);
	}

	@Override
	public boolean eliminarPerfilMesaById(int id) {
		// TODO Auto-generated method stub
		return this.repository.eliminarPerfilMesaById(id);
	}

	@Override
	public PerfilMesa crearPerfilMesa(PerfilMesa perfilmesa) {
		// TODO Auto-generated method stub
		return this.repository.crearPerfilMesa(perfilmesa);
	}

	@Override
	public PerfilMesa getPerfilMesaById(int id) {
		// TODO Auto-generated method stub
		return this.repository.getPerfilMesaById(id);
	}
	


	
}
