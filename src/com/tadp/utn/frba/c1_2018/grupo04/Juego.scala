package com.tadp.utn.frba.c1_2018.grupo04
import Suceso._

trait Jugada {
  val juego: Juego
  def ganancia(monto: Double): Double
  def sucesoGanador(suceso: Suceso) = juego.sucesoGanador(suceso, this)
  def resultadosPosibles() = juego.resultadosPosibles()
  def probabilidad(s: Suceso) = juego.probabilidad(s)
}

trait Juego {
  def distribucion(): Distribucion
  def resultadosPosibles() = distribucion().SucesosPosibles()
  def probabilidad(s: Suceso) = distribucion().Probabilidad(s)
  def sucesoGanador(suceso: Suceso, resultado: Jugada) = suceso == resultado
}