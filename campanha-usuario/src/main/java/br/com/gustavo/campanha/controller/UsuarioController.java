package br.com.gustavo.campanha.controller;

import br.com.gustavo.campanha.model.ApiError;
import br.com.gustavo.campanha.model.Usuario;
import br.com.gustavo.campanha.service.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UsuarioController {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            return usuarioService.create(usuario);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/usuarios/{email}/associar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> associarCampanhas(@PathVariable("email") String email) {
        try {
            Usuario usuario = usuarioService.associarCampanhasUsuarioJaExistente(email);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(new ApiError("Nao foi encontrado nenhum usuario com esse email."), HttpStatus.BAD_REQUEST);
        } catch (ResourceAccessException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(new ApiError("Nao foi possivel concluir a operacao, tente novamente mais tarde."), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}