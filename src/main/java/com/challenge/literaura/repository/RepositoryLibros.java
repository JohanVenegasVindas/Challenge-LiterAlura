package com.challenge.literaura.repository;

import com.challenge.literaura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryLibros extends JpaRepository<Libro, Long> {
    Libro findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdiomas(String idioma);

}
