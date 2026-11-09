package edu.proyecto.texto.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Map;

class PluginLoaderTest {
    private final PluginLoader loader = new PluginLoader();

    @Test
    void cargaPluginJavaPorNombreDeClase() throws Exception {
        TextProcessor p = loader.load("edu.proyecto.texto.plugins.WordCountProcessor");
        assertEquals("wordcount", p.name());
    }

    @Test
    void cargaPluginScala() throws Exception {
        TextProcessor p = loader.load("edu.proyecto.texto.plugins.TopWordsProcessor");
        assertEquals("topwords", p.name());
    }

    @Test
    void claseInexistenteFallaConElegancia() {
        assertThrows(PluginLoader.PluginLoadException.class,
            () -> loader.load("no.existe.Plugin"));
    }

    @Test
    void claseQueNoEsPluginSeRechaza() {
        assertThrows(PluginLoader.PluginLoadException.class,
            () -> loader.load("java.lang.String"));
    }

    @Test
    void loadAllLeeElProperties() throws Exception {
        Map<String, TextProcessor> all = loader.loadAll("plugins.properties");
        assertTrue(all.containsKey("wordcount"));
        assertTrue(all.containsKey("topwords"));
    }
}
