package com.willianrosa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willianrosa.cursomc.domain.Endereco;;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
		
}
