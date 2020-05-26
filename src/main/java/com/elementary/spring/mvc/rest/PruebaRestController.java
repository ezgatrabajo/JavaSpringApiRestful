package com.elementary.spring.mvc.rest;

import com.elementary.spring.mvc.model.Usuario;
import com.elementary.spring.mvc.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/pruebas")
public class PruebaRestController {

    private UsuarioRepository userRepo;

    public PruebaRestController(UsuarioRepository ur){
        this.userRepo = ur;
    }

    @GetMapping("test1")
    public String test1(){
        return "Api Rest test 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "Api Rest test 2";
    }

    @GetMapping("users")
    public List<Usuario> users(){
        return this.userRepo.findAll();
    }

}
