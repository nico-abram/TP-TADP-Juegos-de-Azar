package main

trait JugadaMoneda extends Jugada
case class Cara() extends Suceso with JugadaMoneda
case class Cruz() extends Suceso with JugadaMoneda

object Moneda extends Juego[JugadaMoneda] {
  val d: Distribucion[JugadaMoneda] = Equiprobable(Seq(Cara(), Cruz()))
  def Distribucion() = d
}