package br.reis.eventosapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoFilter {

    private String titulo;
    private String descricao;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataInicio;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataFim;
    private String organizadorNome;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getOrganizadorNome() {
        return organizadorNome;
    }

    public void setOrganizadorNome(String organizadorNome) {
        this.organizadorNome = organizadorNome;
    }
}