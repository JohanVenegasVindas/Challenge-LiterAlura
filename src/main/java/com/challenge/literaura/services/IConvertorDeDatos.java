package com.challenge.literaura.services;

public interface IConvertorDeDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
