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

import com.elementary.spring.mvc.repository.EmployeeRepository;
import com.sun.xml.bind.v2.model.core.ID;
import com.elementary.spring.mvc.model.Employee;
import com.elementary.spring.mvc.model.Marca;

import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/v1/employees")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping()
	public List<Employee> findAll(){
		return repo.findAll();
	}  
	
	@GetMapping(value="/{id}")
	public Employee view(@PathVariable("id") Integer id){
		return repo.findById(id).get();
	}  
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Employee e){
		repo.save(e);
	}

	@PutMapping()
	public void edit(@RequestBody Employee e){
		repo.save(e);
	}

	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") Integer id){
		repo.deleteById(id);
	}
	
}
