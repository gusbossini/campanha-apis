package br.com.gustavo.campanha.service;

import br.com.gustavo.campanha.model.Campanha;
import br.com.gustavo.campanha.repository.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class CampanhaServiceImpl implements ICampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public List<Campanha> findByIdTimeCoracao(Long idTime) {
        return campanhaRepository.findByIdTimeCoracao(idTime);
    }

    @Override
    public List<Campanha> findAll() {
        return campanhaRepository.findAll();
    }

    @Override
    @Transactional
    public Campanha create(Campanha campanha) {
        List<Campanha> campanhas = findAll();
        LocalDate dtNovaCampanha = campanha.getDataVigencia();

        // nao faz distincao por time do coracao pois nao esta descrito no requisito, validar com analista
        campanhas.sort(Comparator.comparing(Campanha::getDataVigencia));
        while (campanhas.stream().filter(c -> c.getDataVigencia().equals(dtNovaCampanha)).findFirst().isPresent()) {
            campanhas.forEach(c -> c.setDataVigencia(c.getDataVigencia().plusDays(1)));
        }

        campanhas.add(campanha);
        campanhaRepository.saveAll(campanhas);

        return campanha;
    }

    /*
     * Aqui nao foi incluido a rotina de atualizar as campanhas existentes com base da data de vigencia
     * porque nao foi especificado no documento, levantar a duvida com o analista.
     */
    @Override
    public Campanha update(Campanha campanha) {
        return campanhaRepository.save(campanha);
    }

    @Override
    public boolean delete(Long id) {
        Campanha campanha = campanhaRepository.getOne(id);
        if (campanha == null) {
            return false;
        }
        campanhaRepository.delete(campanha);
        return true;
    }
}
