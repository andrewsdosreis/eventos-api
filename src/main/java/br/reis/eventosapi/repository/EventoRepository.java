package br.reis.eventosapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.reis.eventosapi.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findAll(Sort sort);
    List<Evento> findByOrganizador_Id(Integer id, Sort sort);
}