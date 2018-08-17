package br.com.gustavo.campanha.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

    /*
     * vou deixar o fetch type eager mesmo pois é uma informacao pequena pro banco trazer
     * evitando assim n+1
     */
    @ManyToOne(optional = false)
    private Time timeCoracao;

    @ManyToMany
    @JoinTable(name = "usuario_campanha",
            joinColumns = @JoinColumn(name = "usuario_email"),
            inverseJoinColumns = @JoinColumn(name = "campanha_id"))
    private List<Campanha> campanhas = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Time getTimeCoracao() {
        return timeCoracao;
    }

    public void setTimeCoracao(Time timeCoracao) {
        this.timeCoracao = timeCoracao;
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }
}