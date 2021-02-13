package com.elementary.spring.mvc.db;

import com.elementary.spring.mvc.model.Usuario;
import com.elementary.spring.mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;


@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        this.userRepo.deleteAll();
        Usuario u1 = new Usuario ("usuario1",encoder.encode("123"), "USER","");
        Usuario u2 = new Usuario ("admin",encoder.encode("123"),  "ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        Usuario u3 = new Usuario ("manager",encoder.encode("123"),  "MANAGER","ACCESS_TEST1");
        List<Usuario> users = Arrays.asList(u1, u2, u3);

        this.userRepo.saveAll(users);

    }
}
