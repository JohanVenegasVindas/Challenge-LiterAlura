package com.challenge.literaura.controller;

import java.util.Scanner;

public class Funciones {
    private String parametroBusqueda;

    public String getParametroBusqueda() {
        return parametroBusqueda;
    }

    public void setParametroBusqueda(String parametroBusqueda) {
        this.parametroBusqueda = parametroBusqueda;
    }

    public void menuOpciones(){
        System.out.println("\nHola bienvenido al menú del catalogo de libros".toUpperCase() +"\n\n" +
                "************************************************\n\n" +
                "1.--> Buscar libro por titulo.\n"+
                "2.--> Listar libros registrados\n" +
                "3.--> Listar autores registrados\n" +
                "4.--> Listar autores vivos en un determinado año\n" +
                "5.--> Listar libros por idioma\n" +
                "0.--> Salir\n" +
                "************************************************\n\n"+
                "¿Que opcion deseas elegir?");
    }


    public String url(int opcion, String urlBase) {
        Scanner teclado = new Scanner(System.in);
        String urlCompleta;
        if (opcion == 1 ) {
            System.out.println("Escribe por favor el titulo del libro que deseas encontrar:");
            this.parametroBusqueda =  teclado.nextLine();
            urlCompleta = urlBase + "?search=" + parametroBusqueda.replace(" ", "%20");
            return urlCompleta;
        }
        return urlBase;

    }



}
