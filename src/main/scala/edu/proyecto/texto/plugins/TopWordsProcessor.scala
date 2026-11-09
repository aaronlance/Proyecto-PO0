package edu.proyecto.texto.plugins

import edu.proyecto.texto.core.{ProcessingException, Result, TextProcessor}

/** Plugin Scala (Rol 3): top-N palabras más frecuentes. Implementa la interfaz Java. */
class TopWordsProcessor extends TextProcessor {
  private val TopN = 5

  override def name(): String = "topwords"

  override def process(text: String): Result = {
    if (text == null) throw new ProcessingException("Texto nulo")
    val t0 = System.currentTimeMillis()
    val top = text.toLowerCase
      .split("[^\\p{L}]+")
      .filter(_.length > 2)
      .groupBy(identity)
      .view.mapValues(_.length)
      .toSeq
      .sortBy { case (w, n) => (-n, w) }
      .take(TopN)
      .map { case (w, n) => s"$w=$n" }
      .mkString(", ")
    new Result(name(), top, System.currentTimeMillis() - t0)
  }
}
