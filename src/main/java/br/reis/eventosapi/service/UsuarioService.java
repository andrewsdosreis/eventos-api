package br.reis.eventosapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.reis.eventosapi.entity.Usuario;
import br.reis.eventosapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Integer idUsuario) {
        return usuarioRepository.findOne(idUsuario);
    }

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public Usuario login(String email, String password) {
        return usuarioRepository.findFirstByEmailAndPassword(email, password);
    }
}