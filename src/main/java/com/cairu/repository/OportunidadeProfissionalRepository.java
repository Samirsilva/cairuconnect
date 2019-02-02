package com.cairu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cairu.model.sqlserver.OportunidadeProfissional;

@Repository
public interface OportunidadeProfissionalRepository extends JpaRepository<OportunidadeProfissional, Integer> {

}
