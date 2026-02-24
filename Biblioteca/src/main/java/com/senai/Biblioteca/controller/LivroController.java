package com.senai.Biblioteca.controller;

import com.senai.Biblioteca.model.Livro;
import com.senai.Biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/Livro")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro save (@RequestBody Livro Livro){
        try {
            return service.salvar(Livro);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> list (){
        try {
            return service.listarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Livro findById(
            @PathVariable long id){
        try{
            return service.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void update (
            @PathVariable long id, @RequestBody Livro livro){
        try {
            service.atualizar(id, livro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        try {
            service.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
