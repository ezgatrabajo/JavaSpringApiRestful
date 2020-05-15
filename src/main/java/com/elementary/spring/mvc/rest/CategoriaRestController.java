package com.elementary.spring.mvc.rest;

import java.net.URI;
import java.util.List;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
//import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elementary.spring.mvc.repository.CategoriaRepository;


import io.swagger.annotations.ApiOperation;

import com.elementary.spring.mvc.model.Categoria;
import com.elementary.spring.mvc.exception.CategoriaNotFoundException;
import com.elementary.spring.mvc.exception.CategoriaCustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
//import org.springframework.hateoas.CollectionModel;
@RestController
@RequestMapping("/v1/categorias")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaRepository repo;
	
	@ApiOperation(
			value="Listado de Categorias",
			notes =" Listado de todas las categorias",
			response = Categoria.class,
			responseContainer="List",
			produces= "application/json")
	@GetMapping()
	public List<Categoria> findAll(){
			List<Categoria> t =repo.findAll(); 
			
			return t;
	}
	
	@GetMapping(value="/{id}")
	public Categoria  view(@PathVariable("id") Integer id){
		//return repo.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
		Categoria c =repo.findById(id).orElseThrow(() -> new CategoriaCustomNotFoundException("No se encontro Categoria id: " + id.toString())); 
		//Resource <Categoria> rc = new Resource<Categoria>(c);
		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
		/*
		return new EntityModel<>(c,
			    linkTo(methodOn(CategoriaRestController.class).view(id)).withSelfRel(),
			    linkTo(methodOn(CategoriaRestController.class).findAll()).withRel("categorias"));
		*/
		return c;
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Categoria e){
		repo.save(e);
	}
	
	@PostMapping("/addnew")
	public ResponseEntity<?> addNew(@RequestBody Categoria e) {
		repo.save(e);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(e.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	

	@PutMapping()
	public ResponseEntity<Categoria> edit(@RequestBody Categoria e){
		
		repo.save(e);
		return new ResponseEntity<Categoria>(e, HttpStatus.OK);
	}

	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id){
		repo.deleteById(id);
	}
	
	@GetMapping("/holamundo")
	public String holamundo() {
		return "holamundo";
	}
	
}
