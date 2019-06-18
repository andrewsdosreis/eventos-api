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
import org.springframework.web.bind.annotation.RestController;

import br.reis.eventosapi.entity.ParticipacaoEvento;
import br.reis.eventosapi.model.ParticipacaoEventoInput;
import br.reis.eventosapi.service.ParticipacaoEventoService;

@RestController
@RequestMapping(path = "/v1/participacaoevento")
public class ParticipacaoEventoController {

    private ParticipacaoEventoService participacaoEventoService;

    @Autowired
    public ParticipacaoEventoController(ParticipacaoEventoService participacaoEventoService) {
        this.participacaoEventoService = participacaoEventoService;
    }

    @GetMapping("/{id}")
    public ParticipacaoEvento find(@PathVariable Integer id) {
        return participacaoEventoService.find(id);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public List<ParticipacaoEvento> listByUsuario(@PathVariable Integer idUsuario) {
        try {
            return participacaoEventoService.listByUsuario(idUsuario);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping()
    public ParticipacaoEvento insert(@RequestBody ParticipacaoEventoInput participacaoEventoInput) {
        try {
            return participacaoEventoService.insert(participacaoEventoInput.getIdUsuario(), participacaoEventoInput.getIdEvento());
        } catch (Exception e) {
            throw e;
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        try {
            participacaoEventoService.delete(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw e;
        }
    }
}