package br.reis.eventosapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "ParticipacaoEvento")
public class ParticipacaoEvento {

    @Id
    @GeneratedValue
    private Integer id;
    private String ticket;

    @ManyToOne()
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public ParticipacaoEvento() {
    }

    public ParticipacaoEvento(String ticket, Evento evento, Usuario usuario) {
        this.ticket = ticket;
        this.evento = evento;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}