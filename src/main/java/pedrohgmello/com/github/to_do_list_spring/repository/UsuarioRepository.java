package pedrohgmello.com.github.to_do_list_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Optional<Usuario> findByUsername(String username);
}
