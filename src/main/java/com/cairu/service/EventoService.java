package com.cairu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cairu.model.Evento;
import com.cairu.repository.EventoRepository;
import com.cairu.request.EventoRequest;
import com.cairu.service.exception.DataIntegrityException;
import com.cairu.service.exception.ObjectNotFoundException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repo;
	

	public Evento find(Integer id) {
		Optional<Evento> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + Evento.class.getName()));
	}

	@Transactional
	public Evento insert(Evento obj) {

		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Evento update(Evento obj) {
		Evento newEvent = find(obj.getId());
		updateData(newEvent, obj);
		return repo.save(newEvent);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possível excluir por que há entidades relacionadas");
		}
	}

	public List<Evento> findAll() {
		return repo.findAllByOrderByIdDesc();
	}

	public Page<Evento> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Evento fromDTO(EventoRequest noticeReq) {
/*		Usuario usuario = service.find(noticeReq.getIdUsuario());
		if(usuario == null) {	
			throw new IllegalArgumentException("Não existe usuario autorizado com esse id");
		}
		return new Noticia(notic, informe, texto, linkImg, fonte, data, nomeLink, video);*/
		return null;
	}

	private void updateData(Evento newEvent, Evento event) {
		newEvent.setNome(event.getNome());
		newEvent.setDescricao(event.getDescricao());
	}
}
