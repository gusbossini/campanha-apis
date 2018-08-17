package br.com.gustavo.campanha.repository;

import br.com.gustavo.campanha.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    @Query("SELECT c FROM Campanha c WHERE c.timeCoracaoId = :idTime AND c.dataVigencia >= CURRENT_DATE")
    List<Campanha> findByIdTimeCoracao(@Param("idTime") Long idTime);

    @Query("SELECT c FROM Campanha c WHERE c.dataVigencia >= CURRENT_DATE")
    List<Campanha> findAll();

}