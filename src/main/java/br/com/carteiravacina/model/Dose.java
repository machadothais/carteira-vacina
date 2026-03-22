package br.com.carteiravacina.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dose")
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dose")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vacina", nullable = false)
    private Vacina vacina;

    @Column(name = "descricao_dose")
    private String descricao;

    @Column(name = "idade_recomendada_aplicacao")
    private Integer idadeRecomendada;

    public Dose() {}

    public Long getId() {
        return id;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdadeRecomendada() {
        return idadeRecomendada;
    }

    public void setIdadeRecomendada(Integer idadeRecomendada) {
        this.idadeRecomendada = idadeRecomendada;
    }
}