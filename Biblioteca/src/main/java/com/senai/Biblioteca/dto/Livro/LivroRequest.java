package com.senai.Biblioteca.dto.Livro;

public record LivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
