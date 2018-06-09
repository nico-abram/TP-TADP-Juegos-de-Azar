package main

trait Suceso {
  def Paso(s: Suceso): Boolean = s == this
}

trait Distribucion[T<:Suceso] extends Suceso {
  def SucesosPosibles(): Seq[T]
  def Probabilidad(x: Suceso): Double
}

case class EventoSeguro[T<:Suceso](val suceso: T) extends Distribucion[T] {
  def SucesosPosibles(): Seq[T] = Seq(suceso)
  def Probabilidad(x: Suceso): Double = if (x == suceso) 100.0 else 0.0
}
case class Equiprobable[T<:Suceso](val SucesosPosibles: Seq[T]) extends Distribucion[T] {
  def Probabilidad(x: Suceso): Double =
    if (SucesosPosibles.contains(x)) 100.0 / SucesosPosibles.length else 0.0
}
case class Ponderada[T<:Suceso](val Sucesos: Seq[(T, Double)]) extends Distribucion[T] {
  def SucesosPosibles(): Seq[T] = Sucesos.map(_._1)
  def Probabilidad(x: Suceso): Double = Sucesos.find(_._1 == x) match {
    case Some(s) => s._2 / Sucesos.map(_._2).sum
    case None    => 0.0
  }
}