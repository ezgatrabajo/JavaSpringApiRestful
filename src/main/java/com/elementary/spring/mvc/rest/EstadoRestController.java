package com.elementary.spring.mvc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.elementary.spring.mvc.repository.EstadoRepository;
import com.elementary.spring.mvc.model.Estado;
import com.elementary.spring.mvc.model.Marca;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/v1/estados")
public class EstadoRestController {
	
	@Autowired
	private EstadoRepository repo;
	
	@GetMapping()
	public List<Estado> findAll(){
		return repo.findAll();
	}  
	
	@GetMapping(value="/{id}")
	public Estado view(@PathVariable("id") Integer id){
		return repo.findById(id).get();
	}  
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Estado e){
		repo.save(e);
	}

	@PutMapping()
	public void edit(@RequestBody Estado e){
		repo.save(e);
	}

	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") Integer id){
		repo.deleteById(id);
	}
	
}
