package com.senai.Biblioteca.controller;

import com.senai.Biblioteca.model.Usuario;
import com.senai.Biblioteca.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario save (@RequestBody Usuario usuario){
        try {
            return service.salvar(usuario);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> list (){
        try {
            return service.listarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Usuario findById(
            @PathVariable long id){
        try{
            return service.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void update (
            @PathVariable long id, @RequestBody Usuario usuario){
        try {
            service.atualizar(id, usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable Long id){
        try {
            service.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
