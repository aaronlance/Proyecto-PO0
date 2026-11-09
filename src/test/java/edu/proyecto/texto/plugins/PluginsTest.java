package edu.proyecto.texto.plugins;

import static org.junit.jupiter.api.Assertions.*;
import edu.proyecto.texto.core.*;
import org.junit.jupiter.api.Test;

class PluginsTest {
    @Test
    void wordCountCuentaPalabrasYLineas() throws Exception {
        Result r = new WordCountProcessor().process("hola mundo\nhola java");
        assertEquals("palabras=4, lineas=2", r.output());
    }

    @Test
    void textoVacioDaCero() throws Exception {
        assertEquals("palabras=0, lineas=0", new WordCountProcessor().process("").output());
    }

    @Test
    void topWordsOrdenaPorFrecuencia() throws Exception {
        Result r = new TopWordsProcessor().process("gato perro gato casa gato perro");
        assertTrue(r.output().startsWith("gato=3, perro=2"));
    }

    @Test
    void failingLanzaExcepcion() {
        assertThrows(ProcessingException.class, () -> new FailingProcessor().process("x"));
    }
}
