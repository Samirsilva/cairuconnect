package com.cairu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cairu.model.OportunidadeProfissional;
import com.cairu.repository.OportunidadeProfissionalRepository;
import com.cairu.request.OportunidadeProfissionalRequest;
import com.cairu.service.exception.DataIntegrityException;
import com.cairu.service.exception.ObjectNotFoundException;

@Service
public class OportunidadeProfissionalService {

	@Autowired
	private OportunidadeProfissionalRepository repo;
	

	public OportunidadeProfissional find(Integer id) {
		Optional<OportunidadeProfissional> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OportunidadeProfissional.class.getName()));
	}

	public OportunidadeProfissional insert(OportunidadeProfissional obj) {

		obj.setId(null);
		return repo.save(obj);
	}

	public OportunidadeProfissional update(OportunidadeProfissional obj) {
		OportunidadeProfissional newObj = find(obj.getId());
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possível excluir um objeto que possúi produtos");
		}
	}

	public List<OportunidadeProfissional> findAll() {
		return repo.findAll();
	}

	public Page<OportunidadeProfissional> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}
	
	public OportunidadeProfissional fromDTO(OportunidadeProfissionalRequest opRequest) {
		return new OportunidadeProfissional(opRequest.getId(), opRequest.getNomeEmpresa(), opRequest.getSexo()
				, opRequest.getEmail(), opRequest.getTipoVaga(),opRequest.getCargaHoraria()
				, opRequest.getRemuneracao(), opRequest.getBeneficios(), opRequest.getRequisitos()
				, opRequest.getDisponibilidade(), opRequest.getQuantidadeVagas(), opRequest.getLinkImg());
	}

}
