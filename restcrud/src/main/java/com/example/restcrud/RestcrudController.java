package com.example.restcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class RestcrudController {
    private List<Usuario> usuarios = new ArrayList<>();

    //Função: Read - Geral
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    //Função: Create
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        usuario.setId(generateNextId()); //atribui um id unico
        usuarios.add(usuario);
        return usuario;
    }

    //Função: Update
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioExistente = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        usuarioExistente.setNome(usuario.getNome());
        //atualize outros atibutos se necessário

        return usuarioExistente;

    }


}
