package br.com.gustavo.campanha.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Como nao existe especificacao de como deve ser gerado ID deixei auto mesmo,
 * nao foi especificado nome das colunas entao deixei default,
 * na especificacao nao descreve se existem colunas nulas ou nao mas achei melhor prevenir
 */
@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long timeCoracaoId;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataVigencia;

    public Campanha() {
    }

    public Campanha(Long id, String nome, Long timeCoracaoId, LocalDate dataVigencia) {
        this.id = id;
        this.nome = nome;
        this.timeCoracaoId = timeCoracaoId;
        this.dataVigencia = dataVigencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTimeCoracaoId() {
        return timeCoracaoId;
    }

    public void setTimeCoracaoId(Long timeCoracaoId) {
        this.timeCoracaoId = timeCoracaoId;
    }

    public LocalDate getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(LocalDate dataVigencia) {
        this.dataVigencia = dataVigencia;
    }
}