package com.tadp.utn.frba.c1_2018.grupo04

trait JugadaRuleta extends Jugada {
  def Ganancia(x: Double) = x * 2
  val juego = Ruleta
}
case class Par() extends JugadaRuleta
case class Impar() extends JugadaRuleta
case class Rojo() extends JugadaRuleta
case class Negro() extends JugadaRuleta
case class Docena(docena: Int) extends JugadaRuleta {
  override def Ganancia(x: Double) = x * 3
}
case class Numero(numero: Int) extends Suceso with JugadaRuleta {
  override def Ganancia(x: Double) = x * 36
}

object Ruleta extends Juego {
  private val sucesos: Seq[JugadaRuleta] = Range(0, 36).map(Numero(_))
  val d: Distribucion =
    Equiprobable(sucesos)
  def Distribucion() = d
  override def SucesoGanador(suceso: Suceso, resultado: Jugada) = resultado match {
    case Numero(i) => suceso match {
      case Par()   => i % 2 == 0
      case Impar() => !SucesoGanador(Par(), resultado)
      case Rojo() => i match {
        case 1 | 3 | 5 | 7 | 9 | 12 | 14 | 16 | 18 | 19 | 21 | 23 | 25 | 27 | 30 | 32 | 34 | 36 => true
        case _ => false
      }
      case Negro()   => !SucesoGanador(Negro(), resultado)
      case Docena(j) => i > 12 * (j - 1) && i <= 12 * j
      case Numero(j) => i == j
    }
    case _ => false
  }
}