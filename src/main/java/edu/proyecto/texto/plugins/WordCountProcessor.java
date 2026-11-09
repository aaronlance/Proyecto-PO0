package edu.proyecto.texto.plugins;

import edu.proyecto.texto.core.*;

/** Plugin Java rápido: cuenta palabras y líneas. Rol 2. */
public class WordCountProcessor implements TextProcessor {
    @Override public String name() { return "wordcount"; }

    @Override
    public Result process(String text) throws ProcessingException {
        if (text == null) throw new ProcessingException("Texto nulo");
        long t0 = System.currentTimeMillis();
        String trimmed = text.trim();
        int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
        int lines = text.isEmpty() ? 0 : text.split("\\R", -1).length;
        String out = "palabras=" + words + ", lineas=" + lines;
        return new Result(name(), out, System.currentTimeMillis() - t0);
    }
}
