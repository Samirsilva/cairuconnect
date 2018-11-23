package com.cairu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cairu.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
