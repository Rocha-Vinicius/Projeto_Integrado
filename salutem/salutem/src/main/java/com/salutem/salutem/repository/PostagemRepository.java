package com.salutem.salutem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salutem.salutem.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {
	public Optional<PostagemModel> findByTituloPostagem (String titulo);
}
