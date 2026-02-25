package com.senai.Biblioteca.controller;

import com.senai.Biblioteca.dto.Livro.LivroRequest;
import com.senai.Biblioteca.dto.Livro.LivroResponse;
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
    public LivroResponse save (@RequestBody LivroRequest request){
        try {
            return service.salvar(request);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<LivroResponse> list (){
        try {
            return service.listarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public LivroResponse findById(
            @PathVariable long id){
        try{
            return service.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void update (
            @PathVariable long id, @RequestBody LivroRequest request){
        try {
            service.atualizar(id, request);
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
