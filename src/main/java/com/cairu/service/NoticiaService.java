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

import com.cairu.model.Noticia;
import com.cairu.repository.NoticiaRepository;
import com.cairu.request.NoticiaRequest;
import com.cairu.service.exception.DataIntegrityException;
import com.cairu.service.exception.ObjectNotFoundException;

@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository repo;
	

	public Noticia find(Integer id) {
		Optional<Noticia> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + Noticia.class.getName()));
	}

	@Transactional
	public Noticia insert(Noticia obj) {

		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Noticia update(Noticia obj) {
		Noticia newNotice = find(obj.getId());
		updateData(newNotice, obj);
		return repo.save(newNotice);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possível excluir por que há entidades relacionadas");
		}
	}

	public List<Noticia> findAll() {
		return repo.findAllByOrderByIdDesc();
	}

	public Page<Noticia> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Noticia fromDTO(NoticiaRequest noticeReq) {
/*		Usuario usuario = service.find(noticeReq.getIdUsuario());
		if(usuario == null) {	
			throw new IllegalArgumentException("Não existe usuario autorizado com esse id");
		}
		return new Noticia(notic, informe, texto, linkImg, fonte, data, nomeLink, video);*/
		return null;
	}

	private void updateData(Noticia newNotice, Noticia notice) {
		newNotice.setInforme(notice.getInforme());
		newNotice.setFonte(notice.getFonte());
	}
}
