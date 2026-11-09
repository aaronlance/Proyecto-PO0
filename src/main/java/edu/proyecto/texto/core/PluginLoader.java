package edu.proyecto.texto.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Factory de plugins con Reflection (Rol 3).
 * Lee plugins.properties (alias=clase.completa) y crea instancias con Class.forName.
 */
public class PluginLoader {

    /** Carga un plugin por nombre de clase. Falla con mensaje claro si no existe. */
    public TextProcessor load(String className) throws PluginLoadException {
        try {
            Class<?> clazz = Class.forName(className);
            if (!TextProcessor.class.isAssignableFrom(clazz)) {
                throw new PluginLoadException(className + " no implementa TextProcessor");
            }
            return (TextProcessor) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new PluginLoadException("Clase no encontrada: " + className, e);
        } catch (ReflectiveOperationException e) {
            throw new PluginLoadException("No se pudo instanciar: " + className, e);
        }
    }

    /** Carga todos los plugins declarados en un .properties del classpath. */
    public Map<String, TextProcessor> loadAll(String resource) throws PluginLoadException {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resource)) {
            if (in == null) throw new PluginLoadException("No existe el recurso: " + resource);
            props.load(in);
        } catch (IOException e) {
            throw new PluginLoadException("Error leyendo " + resource, e);
        }
        Map<String, TextProcessor> plugins = new LinkedHashMap<>();
        for (String alias : props.stringPropertyNames()) {
            plugins.put(alias, load(props.getProperty(alias).trim()));
        }
        return plugins;
    }

    public static class PluginLoadException extends Exception {
        public PluginLoadException(String m) { super(m); }
        public PluginLoadException(String m, Throwable c) { super(m, c); }
    }
}
