package com.elementary.spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elementary.spring.mvc.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByUsername(String nombre);
}

