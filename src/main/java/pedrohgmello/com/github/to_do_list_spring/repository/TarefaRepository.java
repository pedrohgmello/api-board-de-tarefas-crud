package pedrohgmello.com.github.to_do_list_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pedrohgmello.com.github.to_do_list_spring.model.Tarefa;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {
    Set<Tarefa> findByUsuario(Usuario usuario);

}
