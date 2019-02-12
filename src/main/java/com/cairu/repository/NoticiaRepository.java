package com.cairu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cairu.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

	public List<Noticia> findAllByOrderByIdDesc();
}
