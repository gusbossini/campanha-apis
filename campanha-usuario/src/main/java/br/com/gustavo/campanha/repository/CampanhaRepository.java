package br.com.gustavo.campanha.repository;

import br.com.gustavo.campanha.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
