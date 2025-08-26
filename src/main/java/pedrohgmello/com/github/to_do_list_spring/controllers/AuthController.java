package pedrohgmello.com.github.to_do_list_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pedrohgmello.com.github.to_do_list_spring.dto.LoginRequestDTO;
import pedrohgmello.com.github.to_do_list_spring.dto.RegistroRequestDTO;
import pedrohgmello.com.github.to_do_list_spring.dto.TokenResponseDTO;
import pedrohgmello.com.github.to_do_list_spring.dto.UsuarioResponseDTO;
import pedrohgmello.com.github.to_do_list_spring.model.Usuario;
import pedrohgmello.com.github.to_do_list_spring.security.TokenService;
import pedrohgmello.com.github.to_do_list_spring.services.UsuarioService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioService usuarioService;



    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequest.login(),
                loginRequest.password()
        );
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        UserDetails usuarioAutenticado = (UserDetails) auth.getPrincipal();
        String token = tokenService.geradorDeToken(usuarioAutenticado);
        return org.springframework.http.ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody RegistroRequestDTO registerRequest){
        Usuario usuario = usuarioService.criarUsuario(registerRequest.login(), registerRequest.password());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // Pega a URL base da requisição atual (ex: /register)
                .path("/{id}") // Adiciona um segmento de path com uma variável
                .buildAndExpand(usuario.getId()) // Substitui a variável {id} pelo ID do usuário
                .toUri();
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(usuario);
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
