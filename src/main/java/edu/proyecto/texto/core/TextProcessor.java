package edu.proyecto.texto.core;

/** Contrato que cumple todo plugin. Rol 1. */
public interface TextProcessor {
    /** Nombre único del plugin (se usa en métricas y selector). */
    String name();

    /** Procesa el texto y devuelve el resultado. */
    Result process(String text) throws ProcessingException;
}
