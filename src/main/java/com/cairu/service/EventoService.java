package com.cairu.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cairu.model.Evento;
import com.cairu.model.Usuario;
import com.cairu.repository.EventoRepository;
import com.cairu.request.EventoRequest;
import com.cairu.service.exception.DataIntegrityException;
import com.cairu.service.exception.ObjectNotFoundException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	public Evento find(Integer id) {
		Optional<Evento> obj = eventoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + Evento.class.getName()));
	}

	public Evento insert(Evento obj) {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate data = LocalDate.parse(obj.getData());
        String dataFormatada = formatter.format(data);

		obj.setId(null);
		obj.setData(dataFormatada);
		obj = eventoRepository.save(obj);
		return obj;
	}
	
	public Evento update(Evento obj) {
		Evento newEvent = find(obj.getId());
		updateData(newEvent, obj);
		return eventoRepository.save(newEvent);
	}

	public void delete(Integer id) {
		find(id);
		try {
			eventoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possível excluir por que há entidades relacionadas");
		}
	}

	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	public Page<Evento> findPageEvento(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return eventoRepository.findAll(pageRequest);
	}

	public Evento fromRequest(EventoRequest eventReq) {
		
		Usuario usuario = usuarioService.find(eventReq.getIdUsuario());
		if(usuario == null) {
			throw new IllegalArgumentException("Não existe usuario autorizado com esse id");
		}
		
		Evento evento = new Evento();
		evento.setId(eventReq.getId());
		evento.setNome(eventReq.getNome());
		evento.setData(eventReq.getData());
		evento.setDescricao(eventReq.getDescricao());
		evento.setIdUsuario(usuario);
		return evento;
	}
	
	private void updateData(Evento newEvent, Evento event) {
		newEvent.setNome(event.getNome());
		newEvent.setData(event.getData());  
		newEvent.setDescricao(event.getDescricao());
	}
}
