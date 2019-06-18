package br.reis.eventosapi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity()
@Table(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue
    private Integer id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date data;
    @ManyToOne()
    @JoinColumn(name = "id_Usuario")
    private Usuario organizador;

    public Evento() {
    }

    public Evento(Integer id, String titulo, String descricao, Date data, Usuario organizador) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.organizador = organizador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }
}