package com.elementary.spring.mvc.repository;

import com.elementary.spring.mvc.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

}

