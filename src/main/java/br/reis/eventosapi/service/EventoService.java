package br.reis.eventosapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.reis.eventosapi.entity.Evento;
import br.reis.eventosapi.entity.ParticipacaoEvento;
import br.reis.eventosapi.model.EventoException;
import br.reis.eventosapi.model.EventoFilter;
import br.reis.eventosapi.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipacaoEventoService participacaoEventoService;

    private Sort sort = new Sort(Sort.Direction.ASC, "data");

    public Evento findById(Integer id) {
        return eventoRepository.findOne(id);
    }

    public List<Evento> list() {
        return eventoRepository.findAll(sort);
    }

    public List<Evento> list(Integer idUsuario) {
        return eventoRepository.findByOrganizador_Id(idUsuario, sort);
    }

    public List<Evento> listByFilters(EventoFilter filtros) {
        List<Evento> eventos = eventoRepository.findAll(sort);
        eventos = eventos
                    .stream()
                    .filter(evento -> filtros.getTitulo() != null ? evento.getTitulo().contains(filtros.getTitulo()) : true)
                    .filter(evento -> filtros.getDescricao() != null ? evento.getDescricao().contains(filtros.getDescricao()) : true)
                    .filter(evento -> filtros.getDataInicio() != null ? evento.getData().after(filtros.getDataInicio()) : true)
                    .filter(evento -> filtros.getDataFim() != null ? evento.getData().before(filtros.getDataFim()) : true)
                    .filter(evento -> filtros.getOrganizadorNome() != null ? evento.getOrganizador().getNome().contains(filtros.getOrganizadorNome()) : true)
                    .collect(Collectors.toList());
        return eventos;
    }

    public Evento find(Integer id) {
        return findById(id);
    }

    public Evento insert(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento update(Integer id, Evento eventoAtualizado) {
        Evento evento = findById(id);
        if (evento != null) {
            evento.setTitulo(eventoAtualizado.getTitulo());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setData(eventoAtualizado.getData());
        }
        return eventoRepository.save(evento);
    }

    public void delete(Integer id) throws EventoException {
        Evento evento = findById(id);
        
        if (evento == null) {
            throw new EventoException("Evento não encontrado no sistema");
        }

        List<ParticipacaoEvento> listParticipacaoEvento = participacaoEventoService.listByEvento(evento);

        if (listParticipacaoEvento.size() > 0) {
            throw new EventoException("Não é possível excluir o Evento pois já existem participantes");
        }

        eventoRepository.delete(findById(id));
    }
}