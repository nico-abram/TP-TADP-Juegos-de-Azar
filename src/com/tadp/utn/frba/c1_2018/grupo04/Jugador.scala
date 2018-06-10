package com.tadp.utn.frba.c1_2018.grupo04

abstract class Jugador(val montoInicial:Double) {
  def criterio(p:(SucesoGanancia, Double)):Double
  def elegirJuego(juegos:Seq[ApuestaCompuesta]):ApuestaCompuesta = 
    juegos.maxBy(_.apply(montoInicial).aplanar().map((p)=>criterio(p)).sum)
}

case class Racional(override val montoInicial:Double) extends Jugador(montoInicial) {
  def criterio(p:(SucesoGanancia, Double)) = p._1*p._2
}
case class Arriesgado(override val montoInicial:Double) extends Jugador(montoInicial) {
  def criterio(p:(SucesoGanancia, Double)) = p._1.monto
}
case class Cauto(override val montoInicial:Double) extends Jugador(montoInicial) {
  def criterio(p:(SucesoGanancia, Double)) = 
    if(p._1.monto >= 0) p._2 else 0.0
}
case class Customizable(override val montoInicial:Double, val planDeJuego:{def apply(p:(SucesoGanancia, Double)):Double}) extends Jugador(montoInicial) {
  def criterio(p:(SucesoGanancia, Double)):Double = planDeJuego(p)
}