package br.com.gustavo.campanha.service;

import br.com.gustavo.campanha.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface IUsuarioService {

    ResponseEntity<?> create(Usuario usuario) throws IOException;

    Optional<Usuario> findById(String email);

    Usuario associarCampanhasUsuarioJaExistente(String email) throws IOException, NoSuchElementException;

}