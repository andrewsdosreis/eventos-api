package br.reis.eventosapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.reis.eventosapi.entity.Evento;
import br.reis.eventosapi.entity.ParticipacaoEvento;
import br.reis.eventosapi.entity.Usuario;
import br.reis.eventosapi.model.EventoException;
import br.reis.eventosapi.repository.ParticipacaoEventoRepository;

@Service
public class ParticipacaoEventoService {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private ParticipacaoEventoRepository participacaoEventoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    private Sort sort = new Sort(Sort.Direction.ASC, "evento.data");

    public ParticipacaoEvento find(Integer id) {
        return participacaoEventoRepository.findOne(id);
    }

    public List<ParticipacaoEvento> listByUsuario(Integer idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);
        return participacaoEventoRepository.findByUsuario(usuario, sort);
    }

    public List<ParticipacaoEvento> listByEvento(Evento evento) {
        return participacaoEventoRepository.findByEvento(evento);
    }

    public ParticipacaoEvento insert(Integer idUsuario, Integer idEvento) throws EventoException {
        Evento evento = eventoService.findById(idEvento);
        Usuario usuario = usuarioService.findById(idUsuario);
        ParticipacaoEvento participacaoEvento = findByUsuarioAndEvento(usuario, evento);
        if (participacaoEvento != null) {
            throw new EventoException("Você já confirmou sua participação neste Evento!");
        }
        participacaoEvento = new ParticipacaoEvento(generateTicket(6), evento, usuario);
        return participacaoEventoRepository.save(participacaoEvento);
    }

    public void delete(Integer id) {
        participacaoEventoRepository.delete(id);
    }

    private ParticipacaoEvento findByUsuarioAndEvento(Usuario usuario, Evento evento) {
        return participacaoEventoRepository.findFirstByUsuarioAndEvento(usuario, evento);
    }

    private static String generateTicket(int count) {
        StringBuilder builder = new StringBuilder();
        builder.append("#");
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}