package com.tadp.utn.frba.c1_2018.grupo04

case class SucesoGanancia(val monto: Double) extends Suceso {
  def *(d: Double): Double = monto * d
}

trait Resultado extends Suceso {
  type Apuesta = { def monto: Double; def apply(m: Double): Resultado }
  def distribucion() = Ponderada(aplanar())
  def cambiarProba(d: Double): Resultado
  def +(d: Double): Resultado
  def flatMap(f: Apuesta): Resultado
  def aplanar(): Seq[(SucesoGanancia, Double)]
}
case class ResultadoFinal(val monto: Double, val proba: Double) extends Resultado {
  def cambiarProba(d: Double) = ResultadoFinal(monto, d)
  def +(d: Double) = ResultadoFinal(monto + d, proba)
  def flatMap(f: Apuesta): Resultado =
    if (f.monto <= monto) f(monto).cambiarProba(proba)
    else this
  def aplanar() = Seq((SucesoGanancia(monto), proba))
}
case class ResultadoArbol(val Win: Resultado, val Lose: Resultado, val monto: Double = 100.0) extends Resultado {
  def cambiarProba(d: Double) = ResultadoArbol(Win, Lose, d)
  def +(d: Double) = ResultadoArbol(Win + d, Lose + d)
  def flatMap(f: Apuesta): Resultado = ResultadoArbol(Win.flatMap(f), Lose.flatMap(f), monto)
  def aplanar() = (Win.aplanar() ++ Lose.aplanar()).map((p) => (p._1, p._2 * monto / 100.0))
}