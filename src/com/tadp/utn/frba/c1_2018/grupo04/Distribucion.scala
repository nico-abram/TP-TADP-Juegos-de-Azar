package com.tadp.utn.frba.c1_2018.grupo04

object Suceso {
  type Suceso = Any
}
import Suceso._

trait Distribucion {
  def SucesosPosibles(): Seq[Suceso]
  def Probabilidad(x: Suceso): Double
}

case class EventoSeguro(val suceso: Suceso) extends Distribucion {
  def SucesosPosibles(): Seq[Suceso] = Seq(suceso)
  def Probabilidad(x: Suceso): Double = if (x == suceso) 100.0 else 0.0
}
case class Equiprobable(val SucesosPosibles: Seq[Suceso]) extends Distribucion {
  def Probabilidad(x: Suceso): Double =
    if (SucesosPosibles.contains(x)) 100.0 / SucesosPosibles.length else 0.0
}
case class Ponderada(val Sucesos: Seq[(Suceso, Double)]) extends Distribucion {
  def SucesosPosibles(): Seq[Suceso] = Sucesos.map(_._1)
  def Probabilidad(x: Suceso): Double = Sucesos.find(_._1 == x) match {
    case Some(s) => s._2 / Sucesos.map(_._2).sum
    case None    => 0.0
  }
}