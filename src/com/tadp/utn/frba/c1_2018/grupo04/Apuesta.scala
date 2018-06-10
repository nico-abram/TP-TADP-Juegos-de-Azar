package com.tadp.utn.frba.c1_2018.grupo04

trait Apuesta {
  def apply(montoInicial: Double): Resultado
}
case class ApuestaSimple(jugada: Jugada, monto: Double) extends Apuesta {
  def apply(montoInicial: Double): Resultado = {
    val (wins: Seq[Suceso], losses: Seq[Suceso]) =
      jugada.ResultadosPosibles().partition((suceso: Suceso) => jugada.SucesoGanador(suceso))
    ResultadoArbol(
      ResultadoFinal(-monto, losses.map(jugada.Probabilidad(_)).sum),
      ResultadoFinal(jugada.Ganancia(monto) - monto, wins.map(jugada.Probabilidad(_)).sum)) + montoInicial
  }
}
case class ApuestaCompuesta(apuestas: Seq[ApuestaSimple]) extends Apuesta {
  def apply(montoInicial: Double): Resultado =
    apuestas.tail.foldLeft(apuestas.head(montoInicial)) { (r: Resultado, ap: ApuestaSimple) => r.map(ap) }
}