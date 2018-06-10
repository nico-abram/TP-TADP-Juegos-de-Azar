package com.tadp.utn.frba.c1_2018.grupo04

trait JugadaMoneda extends Jugada {
  def ganancia(x: Double): Double = x * 2.0
  val juego = Moneda
}
case class Cara() extends Suceso with JugadaMoneda
case class Cruz() extends Suceso with JugadaMoneda

object Moneda extends Juego {
  val d: Distribucion = Equiprobable(Seq(Cara(), Cruz()))
  def distribucion() = d
}