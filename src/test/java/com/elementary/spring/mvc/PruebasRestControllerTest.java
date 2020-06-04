package com.elementary.spring.mvc;

import com.elementary.spring.mvc.model.Categoria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiRestFulSpring2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PruebasRestControllerTest {
	
	@Autowired
	private TestRestTemplate template;
	
	HttpHeaders headers = createHeaders("usuario1", "123");

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));

				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	@Test 
	public void suma() throws Exception {
		assertEquals("1", "1");
	}
	
	@Test
	public void welcome () throws Exception{
		ResponseEntity <String> response = template.getForEntity("/v1/main/welcome",String.class);
		assertThat(response.getBody(), equalTo("welcome"));
		
	}
	
	@Test
	public void categoriaFindAll() throws Exception{
		String expected = "[{\"id\":25,\"nombre\":\"categroia 1\",\"descripcion\":\"descripcion categoria 1\"},{\"id\":48,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":49,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":34,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":35,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":36,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":37,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":38,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":39,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":40,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":41,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":42,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":43,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":44,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":45,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":46,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":47,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"},{\"id\":50,\"nombre\":\"eze\",\"descripcion\":\"eze categoria\"},{\"id\":51,\"nombre\":\"Jill\",\"descripcion\":\"categoria de jill\"}]";
		ResponseEntity<String> response = template.exchange("/v1/categorias", HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	public void categoriaGet() throws Exception{
		String expected = "{\"id\":25,\"nombre\":\"categroia 1\",\"descripcion\":\"descripcion categoria 1\"}";
		
		ResponseEntity<String> response = template.exchange("/v1/categorias/"+25, HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void categoriaAdd1() throws Exception{
		Categoria c = new Categoria (60, "Pepe","los argentos");
		URI location = template.postForLocation("/v1/categorias/addnew", c);
		assertThat(location.getPath(), containsString("/v1/categorias/addnew/"+c.getId()));
	}
	
	@Test
	public void categoriaAdd2() throws Exception{
		
		Categoria c = new Categoria (30, "Jill","categoria de jill");
		ResponseEntity<String> response = template.exchange("/v1/categorias/" 
						, HttpMethod.POST,
						new HttpEntity<>(c, headers), String.class);
		

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}
	@Test
	public void categoriaDelete() throws Exception {

		ResponseEntity<String> response = template.exchange("/v1/categorias/48", HttpMethod.DELETE,
				new HttpEntity<>(null, headers), String.class);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void categoriaUpdate() throws Exception {

		
		String expected = "{\"id\":25,\"nombre\":\"Jill\",\"descripcion\":\"Learn Spring MVC 6\"}";
		Categoria c = new Categoria(25, "Jill", "Learn Spring MVC 6");

		ResponseEntity<String> response = template.exchange("/v1/categorias", HttpMethod.PUT,
				new HttpEntity<>(c, headers), String.class);
		System.out.println("debug1: "+ response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void categoriasAllJWT () throws Exception{

	}
	/* -- UNIT TEST
	//findall categoria
	@Test
	public void retrieveTodos() throws Exception{
		
		//when
		MvcResult result = mvc.perform(MockMvcRequestBuilders
				.get("/v1/categorias")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		//then
		String expected = "[{\"id\":25,\"nombre\":\"categroia 1\",\"descripcion\":\"descripcion categoria 1\"},{\"id\":27,\"nombre\":\"categroia 2\",\"descripcion\":\"descripcion categoria 2\"},{\"id\":30,\"nombre\":\"2sdfgdsdfg\",\"descripcion\":\"descripcion categoria 2\"}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
		
	}
	
	
	//return json test
	@Test
	public void holamundojson() throws Exception{
		mvc.perform(
				MockMvcRequestBuilders
					.get("/v1/categorias/holamundo")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("holamundo")));
	}
		
	//return string test
	@Test
	public void holamundo() throws Exception{
		mvc.perform(
				MockMvcRequestBuilders
					.get("/v1/categorias/holamundo")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().string(equalTo("holamundo")));
	}
	
	*/
	
		
	
}
