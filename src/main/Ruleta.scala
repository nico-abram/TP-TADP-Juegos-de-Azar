package main

trait JugadaRuleta extends Jugada
case class Par() extends Suceso with JugadaRuleta
case class Impar() extends Suceso with JugadaRuleta
case class Rojo() extends Suceso with JugadaRuleta
case class Negro() extends Suceso with JugadaRuleta
case class Docena(docena: Int) extends Suceso with JugadaRuleta
case class Numero(numero: Int) extends Suceso with JugadaRuleta

object Ruleta extends Juego[JugadaRuleta] {
  private val sucesos:Seq[JugadaRuleta] = Range(1, 36).map(Numero(_))
  private val listaPonderada: Seq[(JugadaRuleta, Double)] =
    sucesos.map((s) => (s, 1.0 / sucesos.length))
  val d: Distribucion[JugadaRuleta] =
    Ponderada(listaPonderada)
  def Distribucion() = d
  override def Sucedio(suceso: Suceso, resultado: JugadaRuleta) = resultado match {
    case Numero(i) => suceso match {
      case Par()   => i % 2 == 0
      case Impar() => !Sucedio(Par(), resultado)
      case Rojo() => i match {
        case 1 | 3 | 5 | 7 | 9 | 12 | 14 | 16 | 18 | 19 | 21 | 23 | 25 | 27 | 30 | 32 | 34 | 36 => true
        case _ => false
      }
      case Negro()   => !Sucedio(Negro(), resultado)
      case Docena(j) => i > 12 * (j - 1) && i <= 12 * j
      case Numero(j) => i == j
    }
    case _ => false
  }
}
