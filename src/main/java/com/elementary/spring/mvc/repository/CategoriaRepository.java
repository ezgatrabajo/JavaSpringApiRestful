package com.elementary.spring.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.elementary.spring.mvc.model.Categoria;
import com.elementary.spring.mvc.model.Employee;
import com.elementary.spring.mvc.model.Marca;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}

