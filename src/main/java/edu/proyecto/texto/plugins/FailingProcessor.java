package edu.proyecto.texto.plugins;

import edu.proyecto.texto.core.*;

/** Plugin que siempre falla: sirve para el escenario 3 (fallback del selector). */
public class FailingProcessor implements TextProcessor {
    @Override public String name() { return "failing"; }
    @Override public Result process(String text) throws ProcessingException {
        throw new ProcessingException("Fallo simulado");
    }
}
