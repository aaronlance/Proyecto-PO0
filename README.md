# Procesador de texto adaptable

Núcleo + plugins cargados por Reflection (`Class.forName`). Un selector elige el plugin según
el tamaño del texto y el historial de métricas (JDBC).

## Ejecutar
    mvn test

## Estructura
- `core/`    contrato (`TextProcessor`, `Result`), `PluginLoader` (Reflection)
- `plugins/` plugins Java (y Scala en `src/main/scala`)
- `plugins.properties` registro de plugins (alias=clase)

## Reparto
| Rol | Entregables |
|---|---|
| 1 | Contrato + UML (docs/) + README arquitectura |
| 2 | Plugins Java + pruebas |
| 3 | PluginLoader + plugin Scala + config |
| 4 | Selector adaptable + JDBC + pruebas de 3 escenarios |

## Pendiente (issues sugeridos)
- [ ] `PluginSelector` (reglas: tamaño del texto, tiempo promedio, errores consecutivos)
- [ ] `MetricsRepository` con JDBC/H2 (tabla `executions`: plugin, size, ms, ok, ts)
- [ ] Escenarios: texto corto, texto grande, plugin que falla -> fallback
- [ ] Más plugins (legibilidad, limpiador, idioma por stopwords)
- [ ] Diagrama UML en `docs/`
- [ ] `Main` de consola

## Flujo de trabajo
Rama por feature (`feat/loader`), PR pequeño, 1 revisión de otro compañero, commits `feat:`, `test:`, `docs:`.
