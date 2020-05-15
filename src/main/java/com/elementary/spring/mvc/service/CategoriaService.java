package com.elementary.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elementary.spring.mvc.repository.CategoriaRepository;
import com.elementary.spring.mvc.model.Categoria;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public List <Categoria> findAll() {
		return repo.findAll();
	}
}
