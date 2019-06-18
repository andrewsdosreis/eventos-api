package br.reis.eventosapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.reis.eventosapi.entity.Evento;
import br.reis.eventosapi.entity.ParticipacaoEvento;
import br.reis.eventosapi.entity.Usuario;

@Repository
public interface ParticipacaoEventoRepository extends JpaRepository<ParticipacaoEvento, Integer> {

    List<ParticipacaoEvento> findByUsuario(Usuario usuario, Sort sort);

    List<ParticipacaoEvento> findByEvento(Evento evento);

    ParticipacaoEvento findFirstByUsuarioAndEvento(Usuario usuario, Evento evento);
}