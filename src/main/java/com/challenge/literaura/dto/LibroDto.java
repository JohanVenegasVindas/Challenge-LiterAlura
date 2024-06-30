package com.challenge.literaura.dto;

public record LibroDto(
        Long Id,
        String titulo,
        String autor,
        String idioma,
        Double numeroDeDescargas )
{}
