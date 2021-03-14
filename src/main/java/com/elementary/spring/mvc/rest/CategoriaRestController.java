package com.elementary.spring.mvc.rest;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.elementary.spring.mvc.repository.CategoriaRepository;


import com.elementary.spring.mvc.model.Categoria;
import com.elementary.spring.mvc.exception.CategoriaCustomNotFoundException;
import org.springframework.http.HttpStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/v1/categorias")

public class CategoriaRestController {
	
	@Autowired
	private CategoriaRepository repo;


	public List<Categoria> findAll(){
			List<Categoria> t =repo.findAll();
			return t;
	}

	@GetMapping()
	@CrossOrigin(origins="http://localhost:3000")
	public CollectionModel<EntityModel<Categoria>> all() {

		List<EntityModel<Categoria>> categorias =
				repo.findAll().stream()
				.map(c -> new EntityModel<Categoria>(c,
						linkTo(methodOn(CategoriaRestController.class)
								.view(c.getId())).withSelfRel(),
						linkTo(methodOn(CategoriaRestController.class)
								.findAll()).withRel("categorias")))
						.collect(Collectors.toList());
		CollectionModel<EntityModel<Categoria>> lc = new CollectionModel (categorias, linkTo(methodOn(CategoriaRestController.class).all()).withSelfRel());
		return lc;
	}


	@GetMapping(value="/{id}")
	public EntityModel<Categoria> view(@PathVariable("id") Integer id){
		Categoria c =repo.findById(id).orElseThrow(() -> new CategoriaCustomNotFoundException("No se encontro Categoria id: " + id.toString()));
		return new EntityModel<Categoria>(c,
			    linkTo(methodOn(CategoriaRestController.class).view(id)).withSelfRel(),
			    linkTo(methodOn(CategoriaRestController.class).findAll()).withRel("categorias"));

	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Categoria e){
		repo.save(e);
	}


	@PutMapping()
	@CrossOrigin(origins ="http://localhost:3000")
	public void edit(@RequestBody Categoria e){
		repo.save(e);
	}

	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CrossOrigin(origins ="http://localhost:3000")
	public void delete(@PathVariable("id") Integer id){
		repo.deleteById(id);
	}



}
