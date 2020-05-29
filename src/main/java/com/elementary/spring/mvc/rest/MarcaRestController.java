package com.elementary.spring.mvc.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.elementary.spring.mvc.repository.MarcaRepository;
import com.elementary.spring.mvc.model.Marca;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/v1/marcas")
@CrossOrigin
public class MarcaRestController {
	
	@Autowired
	private MarcaRepository repo;
	
	@GetMapping()
	public List<Marca> findAll(){
		return repo.findAll();
	}  
	
	@GetMapping(value="/{id}")
	public Marca view(@PathVariable("id") Integer id){
		return repo.findById(id).get();
	}  
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Marca e){
		repo.save(e);
	}
	  
	
	@PutMapping()
	public void edit(@RequestBody Marca e){
		repo.save(e);
	}

	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") Integer id){
		repo.deleteById(id);
	}
	
}
