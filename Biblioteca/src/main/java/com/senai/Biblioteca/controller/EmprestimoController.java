package com.senai.Biblioteca.controller;

import com.senai.Biblioteca.model.Emprestimo;
import com.senai.Biblioteca.service.EmprestimoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/Emprestimo")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo save (Emprestimo emprestimo){
        try{
            return service.salvar(emprestimo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Emprestimo> list (){
        try{
            return service.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

