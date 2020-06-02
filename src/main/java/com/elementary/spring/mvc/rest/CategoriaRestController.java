package com.elementary.spring.mvc.rest;

import java.net.URI;
import java.util.List;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
//import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elementary.spring.mvc.repository.CategoriaRepository;


import com.elementary.spring.mvc.model.Categoria;
import com.elementary.spring.mvc.model.Estado;
import com.elementary.spring.mvc.exception.CategoriaNotFoundException;
import com.elementary.spring.mvc.exception.CategoriaCustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
//import org.springframework.hateoas.CollectionModel;


@RestController
@RequestMapping("/v1/categorias")
@CrossOrigin
public class CategoriaRestController {
	
	@Autowired
	private CategoriaRepository repo;

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
	public void edit(@RequestBody Categoria e){
		repo.save(e);
	}

	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id){
		repo.deleteById(id);
	}



	@GetMapping("/all")
	public CollectionModel<Categoria> getAllCustomers() {
		List<Categoria> allCategorias = repo.findAll();

		for (Categoria c : allCategorias) {
			String customerId = String.valueOf(c.getId());
			Link selfLink = linkTo(CategoriaRestController.class).slash(customerId).withSelfRel();
			c.add(selfLink);
		}

		Link link = linkTo(CategoriaRestController.class).withSelfRel();
		CollectionModel<Categoria> result = new CollectionModel<>(allCategorias, link);
		return result;
	}
}
