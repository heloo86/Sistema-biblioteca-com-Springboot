package com.senai.Biblioteca.dto.Livro;

public record LivroResponse(
        Long id,
        String titulo,
        String autor,
        int anoPublicacao
        ) {
}
