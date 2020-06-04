package com.elementary.spring.mvc.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.elementary.spring.mvc.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import com.elementary.spring.mvc.repository.MarcaRepository;
import com.elementary.spring.mvc.model.Marca;

import org.springframework.http.HttpStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/marcas")
@CrossOrigin
public class MarcaRestController {
	
	@Autowired
	private MarcaRepository repo;
	
	public List<Marca> findAll(){
		return repo.findAll();
	}

	@GetMapping()
	public CollectionModel<EntityModel<Marca>> all() {

		List<EntityModel<Marca>> marcas =
				repo.findAll().stream()
						.map(c -> new EntityModel<Marca>(c,
								linkTo(methodOn(MarcaRestController.class)
										.view(c.getId())).withSelfRel(),
								linkTo(methodOn(MarcaRestController.class)
										.findAll()).withRel("marcas")))
						.collect(Collectors.toList());
		CollectionModel<EntityModel<Marca>> lc = new CollectionModel (marcas, linkTo(methodOn(MarcaRestController.class).all()).withSelfRel());
		return lc;
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
