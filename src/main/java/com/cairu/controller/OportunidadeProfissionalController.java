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

import com.cairu.model.sqlserver.OportunidadeProfissional;
import com.cairu.request.OportunidadeProfissionalRequest;
import com.cairu.service.OportunidadeProfissionalService;

@RestController
@RequestMapping(value = "/oportunidades")
public class OportunidadeProfissionalController {

	@Autowired
	private OportunidadeProfissionalService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OportunidadeProfissional> find(@PathVariable Integer id) {
		OportunidadeProfissional obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_OPORTUNIDADE')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OportunidadeProfissionalRequest request) {
		OportunidadeProfissional obj = service.fromDTO(request);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_OPORTUNIDADE')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody OportunidadeProfissionalRequest request, @PathVariable Integer id) {
		OportunidadeProfissional obj = service.fromDTO(request);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_OPORTUNIDADE')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_OPORTUNIDADE', 'USUARIO')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OportunidadeProfissionalRequest>> findAll() {
		List<OportunidadeProfissional> list = service.findAll();
		List<OportunidadeProfissionalRequest> requestList = list.stream().map(obj -> new OportunidadeProfissionalRequest(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(requestList);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_EVENTO', 'USUARIO')")
	@RequestMapping(value = "/page" , method = RequestMethod.GET)
	public ResponseEntity<Page<OportunidadeProfissionalRequest>> findPage(
			@RequestParam(name= "page", defaultValue = "0") Integer page, 
			@RequestParam(name= "linesPage", defaultValue = "24")Integer linesPage, 
			@RequestParam(name= "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name= "direction", defaultValue = "DESC")String direction) {
		
		Page<OportunidadeProfissional> list = service.findPage(page, linesPage, orderBy, direction);
		Page<OportunidadeProfissionalRequest> listDto = list.map(obj -> new OportunidadeProfissionalRequest(obj));

		return ResponseEntity.ok().body(listDto);
	}	
}
