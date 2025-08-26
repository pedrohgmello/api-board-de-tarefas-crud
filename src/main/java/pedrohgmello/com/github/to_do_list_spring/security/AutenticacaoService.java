package pedrohgmello.com.github.to_do_list_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;
import pedrohgmello.com.github.to_do_list_spring.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AutenticacaoService implements UserDetailsService {
    Usuario usuario;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " não encontrado."));
    }
}
