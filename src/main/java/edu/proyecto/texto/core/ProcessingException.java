package edu.proyecto.texto.core;

public class ProcessingException extends Exception {
    public ProcessingException(String message) { super(message); }
    public ProcessingException(String message, Throwable cause) { super(message, cause); }
}
