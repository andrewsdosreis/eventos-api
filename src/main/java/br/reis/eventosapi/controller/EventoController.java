package br.reis.eventosapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.reis.eventosapi.entity.Evento;
import br.reis.eventosapi.model.EventoFilter;
import br.reis.eventosapi.service.EventoService;

@RestController
@RequestMapping(path = "/v1/evento")
public class EventoController {

    private EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping()
    public List<Evento> list() {
        return eventoService.list();
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public List<Evento> listByUsuario(@PathVariable Integer idUsuario) {
        return eventoService.list(idUsuario);
    }

    @PostMapping("/filter")
    public List<Evento> listByFilters(@RequestBody EventoFilter filtros) {
        return eventoService.listByFilters(filtros);
    }

    @GetMapping(value = "/{id}")
    public Evento detail(@PathVariable Integer id) {
        return eventoService.find(id);
    }

    @PostMapping()
    public Evento insert(@RequestBody Evento evento) {
        try {
            return eventoService.insert(evento);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(value = "/{id}")
    public Evento update(@PathVariable Integer id, @RequestBody Evento eventoAtualizado) {
        try {
            return eventoService.update(id, eventoAtualizado);
        } catch (Exception e) {
            throw e;
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public @ResponseBody HttpStatus delete(@PathVariable Integer id) {
        eventoService.delete(id);
        return HttpStatus.OK;
    }
}