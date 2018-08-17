package br.com.gustavo.campanha.controller;

import br.com.gustavo.campanha.model.Campanha;
import br.com.gustavo.campanha.service.CampanhaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
public class CampanhaController {

    @Autowired
    private CampanhaServiceImpl campanhaService;

    @GetMapping(value = "/campanhas/{idTimeCoracao}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Campanha>> findOneById(@PathVariable("idTimeCoracao") Long idTimeCoracao) {
        return new ResponseEntity<>(campanhaService.findByIdTimeCoracao(idTimeCoracao), HttpStatus.OK);
    }

    @GetMapping(value = "/campanhas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Campanha>> findAll() {
        return new ResponseEntity<>(campanhaService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/campanhas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campanha> create(@RequestBody Campanha campanha) {
        return new ResponseEntity<>(campanhaService.create(campanha), HttpStatus.OK);
    }

    @PutMapping(value = "/campanhas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campanha> update(@RequestBody Campanha campanha) {
        return new ResponseEntity<>(campanhaService.update(campanha), HttpStatus.OK);
    }

    @DeleteMapping(value = "/campanhas/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (campanhaService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}