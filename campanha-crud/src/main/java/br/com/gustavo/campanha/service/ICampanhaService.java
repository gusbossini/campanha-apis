package br.com.gustavo.campanha.service;

import br.com.gustavo.campanha.model.Campanha;

import java.util.List;

public interface ICampanhaService {

    List<Campanha> findByIdTimeCoracao(Long id);

    List<Campanha> findAll();

    Campanha create(Campanha campanha);

    Campanha update(Campanha campanha);

    boolean delete(Long id);

}