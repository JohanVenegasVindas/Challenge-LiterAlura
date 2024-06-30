package com.challenge.literaura.principal;



import com.challenge.literaura.controller.Funciones;
import com.challenge.literaura.model.*;
import com.challenge.literaura.repository.RepositoryAutores;
import com.challenge.literaura.repository.RepositoryLibros;
import com.challenge.literaura.services.Api;
import com.challenge.literaura.services.ConvertorDeDatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private Api consumoApi = new Api();
    private final String URL_BASE = "http://gutendex.com/books/";
    private ConvertorDeDatos conversor = new ConvertorDeDatos();
    private RepositoryLibros repositorio;
    private RepositoryAutores repositorioAutor;
    private List<Libro> libros;
    private List<Autor> autor;

    public Principal(RepositoryLibros repository, RepositoryAutores repositoryAutor) {
        this.repositorio = repository;
        this.repositorioAutor = repositoryAutor;
    }

    public void iniciarApp(){
        Funciones obtenerRecurso = new Funciones();
        int opcion;

        while (true){
            obtenerRecurso.menuOpciones();

            try {
                opcion = teclado.nextInt();

                var url = obtenerRecurso.url(opcion,URL_BASE);

                var json = consumoApi.obtenerApi(url);

                teclado.nextLine();

                var datosLibro = conversor.obtenerDatos(json, ResultadoBusqueda.class);

                if (opcion == 1){
                    var tituloBuscado = obtenerRecurso.getParametroBusqueda();

                    Optional<DatosLibro> libroBuscado = datosLibro.libros().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(tituloBuscado.toUpperCase()))
                            .findFirst();
                    if (libroBuscado.isPresent()){
                        DatosLibro datosLibros = libroBuscado.get();
                        DatosAutor datosAutor = datosLibros.datosAutor().get(0);
                        Autor autor = repositorioAutor.findByNombre(datosAutor.nombre());

                        if (autor == null) {
                            autor = new Autor(datosAutor);
                            repositorioAutor.save(autor);
                        }

                        Libro libro = repositorio.findByTituloContainsIgnoreCase(datosLibros.titulo());


                        if (libro == null) {
                            System.out.println("¡Libro encontrado!");
                            libro = new Libro(datosLibros, autor);
                            repositorio.save(libro);
                            System.out.println(libro);
                        }else {
                            System.out.println("Libro ya esta Registrado");
                        }

                    }else {
                        System.out.println("Libro no encontrado");
                    }
                }

                if (opcion == 2){
                    LibrosRegistrados();
                }

                if (opcion == 3){
                    AutoresRegistrados();
                }

                if (opcion == 4) {
                    autorVivoEnAnio();

                }

                if (opcion == 5){
                    librosPorLenguaje();
                }

                if (opcion == 0 || opcion < 0 || opcion > 5){
                    System.out.println("Gracias por haber usado la aplicación! Hasta la proxima.");
                    break;
                }

            } catch (InputMismatchException e){
                System.out.println("Error al ingresar el valor a elegir: " + e);
                teclado.next();
            }


        }

    }

    private void LibrosRegistrados(){
        libros = repositorio.findAll();
        libros.forEach(System.out::println);
    }

    private void AutoresRegistrados(){
        autor = repositorioAutor.findAll();
        autor.forEach(System.out::println);
    }

    private void librosPorLenguaje(){
        System.out.println("""
        --------------------------------
        Ingrese numero de idioma deseado
        
        1- Ingles
        2- Español
        3- Portuges
        4- Italiano
        
        -------------------------------
        """);
        admitirSoloNumeros();
        var  numero = teclado.nextInt();
        switch (numero) {
            case 1:
                buscadorDeIdioma("en");
                break;
            case 2:
                buscadorDeIdioma("es");
                break;
            case 3:
                buscadorDeIdioma("pt");
                break;
            case 4:
                buscadorDeIdioma("it");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void admitirSoloNumeros() {
        while (!teclado.hasNextInt()) {
            System.out.println("ingrese solo numeros");
            teclado.next();
        }

    }

    private void buscadorDeIdioma(String idioma) {
        try {
            libros = repositorio.findByIdiomas(idioma);

            if (libros == null) {
                System.out.println("No hay Libros registrados");
            } else {
                libros.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Error en la busqueda");
        }

    }

    private void autorVivoEnAnio() {
        System.out.println("Ingrese año");
        admitirSoloNumeros();
        var año = teclado.nextInt();
        autor = repositorioAutor.autoresVivosEnDeterminadoAño(año);
        if (autor == null) {
            System.out.println("No hay registro de autores en ese año");
        } else {
            autor.forEach(System.out::println);
        }
    }

    private void LibrosPorLenguajes(){
        System.out.println("""
        --------------------------------
        Ingrese numero de idioma deseado
        
        1- Ingles
        2- Español
        3- Portuges
        4- Italiano
        
        -------------------------------
        """);
        admitirSoloNumeros();
        var  numero = teclado.nextInt();
        switch (numero) {
            case 1:
                buscadorDeIdioma("en");
                break;
            case 2:
                buscadorDeIdioma("es");
                break;
            case 3:
                buscadorDeIdioma("pt");
                break;
            case 4:
                buscadorDeIdioma("it");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }



}
