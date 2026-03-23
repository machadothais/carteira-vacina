package br.com.carteiravacina.model;


import br.com.carteiravacina.enumerator.PublicoAlvoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vacina")
public class Vacina {

    
    public Vacina() {
    }
    
    public Vacina(String nomeVacina, String descricaoVacina, String limiteAplicacao, PublicoAlvoEnum publicoAlvo,
            Dose dose) {
        this.nomeVacina = nomeVacina;
        this.descricaoVacina = descricaoVacina;
        this.limiteAplicacao = limiteAplicacao;

        this.dose = dose;
    }

    public Vacina(Long idVacina, String nomeVacina, String descricaoVacina, String limiteAplicacao,
            PublicoAlvoEnum publicoAlvo, Dose dose) {
        this.idVacina = idVacina;
        this.nomeVacina = nomeVacina;
        this.descricaoVacina = descricaoVacina;
        this.limiteAplicacao = limiteAplicacao;
        this.publicoAlvo = publicoAlvo;
        this.dose = dose;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacina")
    private Long idVacina;
    private String nomeVacina;
    private String descricaoVacina;
    private String limiteAplicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "publico_alvo")
    private PublicoAlvoEnum publicoAlvo;

    @JoinColumn(name = "id_dose")
    @ManyToOne
    private Dose dose;

    public Long getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(Long idVacina) {
        this.idVacina = idVacina;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public String getDescricaoVacina() {
        return descricaoVacina;
    }

    public void setDescricaoVacina(String descricaoVacina) {
        this.descricaoVacina = descricaoVacina;
    }

    public String getLimiteAplicacao() {
        return limiteAplicacao;
    }

    public void setLimiteAplicacao(String limiteAplicacao) {
        this.limiteAplicacao = limiteAplicacao;
    }

    public PublicoAlvoEnum getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(PublicoAlvoEnum publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public Dose getDose() {
        return dose;
    }

    public void setDose(Dose dose) {
        this.dose = dose;
    }

    
    

    
}