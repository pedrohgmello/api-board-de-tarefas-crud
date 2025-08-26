package pedrohgmello.com.github.to_do_list_spring.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedrohgmello.com.github.to_do_list_spring.exceptions.RegraDeNegocioException;
import pedrohgmello.com.github.to_do_list_spring.model.Role;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;
import pedrohgmello.com.github.to_do_list_spring.repository.RoleRepository;
import pedrohgmello.com.github.to_do_list_spring.repository.UsuarioRepository;

import java.util.Set;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario criarUsuario(String login, String senhaPura){
        if(usuarioRepository.findByUsername(login).isPresent()){
            throw new RegraDeNegocioException("O login " + login + " já está em uso.");
        }
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Erro: role 'ROLE_USER' não encontrado na base de dados."));

        String senhaCriptografada = passwordEncoder.encode(senhaPura);

        Usuario usuario = new Usuario();
        usuario.setUsername(login);
        usuario.setPassword(senhaCriptografada);
        usuario.setRoles(Set.of(userRole));

        return usuarioRepository.save(usuario);
    }
}
