package com.tadp.utn.frba.c1_2018.grupo04
import Suceso._

trait JugadaMoneda extends Jugada {
  def ganancia(x: Double): Double = x * 2.0
  val juego = Moneda
}
case class Cara() extends JugadaMoneda
case class Cruz() extends JugadaMoneda

object Moneda extends Juego {
  val d: Distribucion = Equiprobable(Seq(Cara(), Cruz()))
  def distribucion() = d
}