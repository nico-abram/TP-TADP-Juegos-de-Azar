package com.tadp.utn.frba.c1_2018.grupo04

trait Jugada extends Suceso {
  val juego: Juego
  def Ganancia(monto: Double): Double
  def SucesoGanador(suceso: Suceso) = juego.SucesoGanador(suceso, this)
}

trait Juego {
  def Distribucion(): Distribucion
  def ResultadosPosibles() = Distribucion().SucesosPosibles()
  def Probabilidad(s: Suceso) = Distribucion().Probabilidad(s)
  def SucesoGanador(suceso: Suceso, resultado: Jugada) = suceso == resultado
}