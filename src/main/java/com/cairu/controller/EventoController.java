package com.cairu.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cairu.model.Evento;
import com.cairu.request.EventoRequest;
import com.cairu.service.EventoService;

@RestController
@RequestMapping(value = "/eventos")
public class EventoController {

	@Autowired
	private EventoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Evento> find(@PathVariable Integer id) {
		Evento obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_EVENTO')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EventoRequest request) {
		Evento obj = service.fromRequest(request);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_EVENTO')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EventoRequest request, @PathVariable Integer id) {
		Evento obj = service.fromRequest(request);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_EVENTO')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_EVENTO', 'USUARIO')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EventoRequest>> findAll() {
		List<Evento> list = service.findAll();
		List<EventoRequest> listDto = list.stream().map(obj -> new EventoRequest(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_EVENTO')")
	@RequestMapping(value = "/page" , method = RequestMethod.GET)
	public ResponseEntity<Page<EventoRequest>> findPage(
			@RequestParam(name= "page", defaultValue = "0") Integer page, 
			@RequestParam(name= "linesPage", defaultValue = "24")Integer linesPage, 
			@RequestParam(name= "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name= "direction", defaultValue = "DESC")String direction) {
		
		Page<Evento> list = service.findPage(page, linesPage, orderBy, direction);
		Page<EventoRequest> listRequest = list.map(obj -> new EventoRequest(obj));

		return ResponseEntity.ok().body(listRequest);
	}		
}
