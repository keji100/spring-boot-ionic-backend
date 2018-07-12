package com.willianrosa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willianrosa.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
