package br.com.carteiravacina.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Imunizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImunizacao;
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_dose")
    private Dose dose;
    
    
    private LocalDate dataAplicacao;
    private String fabricante;
    private String lote;
    private String localAplicacao;
    private String profissionalAplicador;

    public Imunizacao(){}

    public Imunizacao(
        Integer idImunizacao, 
        Paciente paciente,
        Dose dose,
        LocalDate dataAplicacao, 
        String fabricante, 
        String lote, 
        String localAplicacao, 
        String profissionalAplicador){
            this.idImunizacao = idImunizacao;
            this.paciente = paciente;
            this.dose = dose;
            this.dataAplicacao = dataAplicacao;
            this.fabricante = fabricante;
            this.lote = lote;
            this.localAplicacao = localAplicacao;
            this.profissionalAplicador = profissionalAplicador;
        }

        public Integer getId(){
            return idImunizacao;
        }
        public void setId(Integer idImunizacao){
             this.idImunizacao = idImunizacao;
        }

        public Paciente getPaciente(){
            return paciente;
        }

        public void setPaciente(Paciente paciente){
            this.paciente = paciente;
        }

        public Dose getDose(){
            return dose;
        }

        public void setDose(Dose dose){
            this.dose = dose;
        }

        public LocalDate getDataAplicacao(){
            return dataAplicacao;
        }
        public void setDataAplicacao(LocalDate dataAplicacao){
             this.dataAplicacao = dataAplicacao;
        }

        public String getFabricante(){
            return fabricante;
        }
        public void setFabricante(String fabricante){
             this.fabricante = fabricante;
        }

         public String getLote(){
            return lote;
        }
        public void setLote(String lote){
             this.lote = lote;
        }

        public String getLocalAplicacao(){
            return localAplicacao;
        }
        public void setLocalAplicacao(String localAplicacao){
             this.localAplicacao = localAplicacao;
        }

        public String getProfissionalAplicador(){
            return profissionalAplicador;
        }
        public void setProfissionalAplicador(String profissionalAplicador){
             this.profissionalAplicador = profissionalAplicador;
        }


}
