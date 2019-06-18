package br.reis.eventosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.reis.eventosapi.entity.Usuario;
import br.reis.eventosapi.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @CrossOrigin
    @PostMapping(path = "/login")
    public Usuario login(@RequestBody Usuario usuario) {
        try {
            return usuarioService.login(usuario.getEmail(), usuario.getPassword());
        } catch (Exception e) {
            throw e;
        }
    }
}