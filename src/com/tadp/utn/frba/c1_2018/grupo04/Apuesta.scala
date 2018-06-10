package com.tadp.utn.frba.c1_2018.grupo04

trait Apuesta {
  def apply(montoInicial: Double): Resultado
}
case class ApuestaSimple(jugada: Jugada, monto: Double) extends Apuesta {
  def apply(montoInicial: Double): Resultado = {
    val distribucion = jugada.juego.Distribucion()
    val (wins: Seq[Suceso], loses: Seq[Suceso]) =
      distribucion.SucesosPosibles().partition((suceso: Suceso) => jugada.SucesoGanador(suceso))
    ResultadoArbol(
      ResultadoFinal(-monto, loses.map(distribucion.Probabilidad(_)).sum),
      ResultadoFinal(jugada.Ganancia(monto) - monto, wins.map(distribucion.Probabilidad(_)).sum)) + montoInicial
  }
}
case class ApuestaCompuesta(apuestas: Seq[ApuestaSimple]) extends Apuesta {
  def apply(montoInicial: Double): Resultado =
    apuestas.tail.foldLeft(apuestas.head(montoInicial)) { (r: Resultado, ap: ApuestaSimple) => r.map(ap) }
}