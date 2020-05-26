package com.elementary.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elementary.spring.mvc.model.Employee;
import com.elementary.spring.mvc.model.Usuario;
import com.elementary.spring.mvc.repository.EmployeeRepository;
import com.elementary.spring.mvc.repository.UsuarioRepository;

@Controller
@RequestMapping("employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private BCryptPasswordEncoder encoder; 
	
	
	
	@GetMapping("createuser")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		
		
		Usuario u = new Usuario();
		u.setId(3);
		u.setUsername("usuario1");
		u.setPassword(encoder.encode("123"));
		//u.setPassword("123");
		Usuario ret = repoUsuario.save(u);
		
		return "employees/greeting";
	}
	
	@GetMapping("/add")
	public String add(@RequestBody Employee e) {
		//Employee e = new Employee(0,"ezequiel","galvan",80000);
		repo.save(e);
		//model.addAttribute("employee", e);
		return "employees/greeting";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("lista", repo.findAll());
		return "employees/list";
	}

}