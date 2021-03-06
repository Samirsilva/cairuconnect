package com.cairu.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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

import com.cairu.model.OportunidadeProfissional;
import com.cairu.request.OportunidadeProfissionalRequest;
import com.cairu.service.OportunidadeProfissionalService;


@RestController
@RequestMapping(value = "/oportunidades")
public class OportunidadeProfissionalController {

    private static final Logger LOGGER = Logger.getLogger(OportunidadeProfissionalController.class.getName());

	@Autowired
	private OportunidadeProfissionalService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OportunidadeProfissional> find(@PathVariable Integer id) {
		OportunidadeProfissional obj = service.find(id);
		
		OportunidadeProfissional objNovo = obj;

		if(objNovo.getSexo().equals("F")) {
			objNovo.setSexo("Feminino");
		}
		if(objNovo.getSexo().equals("A")) {
			LOGGER.info("entrou");
			objNovo.setSexo("Ambos");
		}
		if(objNovo.getSexo().equals("M")) {
			objNovo.setSexo("Masculino");
		}
		
		if(objNovo.getTipoVaga().equals("clt")) {
			objNovo.setTipoVaga("Emprego");
		}
		if(objNovo.getTipoVaga().equals("est")) {
			objNovo.setTipoVaga("Estágio");
		}
		return ResponseEntity.ok().body(objNovo);
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
		List<OportunidadeProfissionalRequest> requestListNova = new ArrayList<OportunidadeProfissionalRequest>();
		for(OportunidadeProfissionalRequest o : requestList) {
			LOGGER.info("ANTES: " +o.getSexo());
			if(o.getSexo().equals("F")) {
				o.setSexo("Feminino");
			}
			if(o.getSexo().equals("A")) {
				LOGGER.info("entrou");
				o.setSexo("Ambos");
			}
			if(o.getSexo().equals("M")) {
				o.setSexo("Masculino");
			}
			
			if(o.getTipoVaga().equals("clt")) {
				o.setTipoVaga("Emprego");
			}
			if(o.getTipoVaga().equals("est")) {
				o.setTipoVaga("Estágio");
			}
			LOGGER.info("Depois: " +o.getSexo());
			requestListNova.add(o);
		}
		return ResponseEntity.ok().body(requestListNova);
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
