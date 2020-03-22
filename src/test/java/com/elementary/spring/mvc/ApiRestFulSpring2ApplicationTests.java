package com.elementary.spring.mvc;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.elementary.spring.mvc.model.Usuario;
import com.elementary.spring.mvc.repository.UsuarioRepository;

@SpringBootTest
class ApiRestFulSpring2ApplicationTests {

	private UsuarioRepository repo;
	@Test
	void contextLoads() {
	}
	
	@Test
	void crearUsuarioTest() {
		Usuario u = new Usuario(0,"developer","aqswdefr");
		Usuario ret = repo.save(u);
		
		//assertTrue(ret.getPassword().equalsIgnoreCase(u.getUsername()));
		
	}
}
