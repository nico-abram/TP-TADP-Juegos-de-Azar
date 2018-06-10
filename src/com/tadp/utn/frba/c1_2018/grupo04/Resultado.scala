package com.tadp.utn.frba.c1_2018.grupo04

case class SucesoGanancia(val monto: Double) extends Suceso {
  def *(d: Double): Double = monto * d
}

trait Resultado extends Suceso {
  type Apuesta = { def monto: Double; def apply(m: Double): Resultado }
  def +(d: Double): Resultado
  def map(f: Apuesta): Resultado
  def aplanar(): Seq[(SucesoGanancia, Double)]
}
case class ResultadoCompuesto(val r: Resultado, val proba: Double) extends Resultado {
  def +(d: Double): Resultado = ResultadoCompuesto(r + d, proba)
  def map(f: Apuesta): Resultado = ResultadoCompuesto(r.map(f), proba)
  def aplanar() = r.aplanar().map((pair) => (pair._1, pair._2 * proba / 100))
}
case class ResultadoFinal(val monto: Double, val proba: Double) extends Resultado {
  def +(d: Double): Resultado = ResultadoFinal(monto + d, proba)
  def map(f: Apuesta): Resultado =
    if (f.monto <= monto) ResultadoCompuesto(f(monto), proba)
    else this
  def aplanar() = Seq((SucesoGanancia(monto), proba))
}
case class ResultadoArbol(val Win: Resultado, val Lose: Resultado) extends Resultado {
  def +(d: Double): Resultado = ResultadoArbol(Win + d, Lose + d)
  def map(f: Apuesta): Resultado = ResultadoArbol(Win.map(f), Lose.map(f))
  def aplanar() = Win.aplanar() ++ Lose.aplanar()
}