package main

trait Apuesta {
  def Ganancias[T<:Jugada](j: Juego[T], res: T): Double
  def Ganada[T<:Jugada](j: Juego[T], res: T): Boolean
  def apply[T<:Jugada](j: Juego[T]): Distribucion[Resultado] =
    Ponderada(
      for (r <- j.ResultadosPosibles()) yield if (Ganada(j, r))
        (Win(Ganancias(j, r), r), j.Probabilidad(r))
      else
        (Lose(r), 100 - j.Probabilidad(r)))
  def MontoDeProbabilidad[T<:Jugada](monto:Double, j:Juego[T], jugada:Suceso) = 
    monto * 100 / j.Probabilidad(jugada)
}
case class ApuestaSimple(jugada: Suceso, monto: Double) extends Apuesta {
  def Ganada[T<:Jugada](j: Juego[T], res: T): Boolean =
    j.Sucedio(jugada, res)
  def Ganancias[T<:Jugada](j: Juego[T], res: T) =
    MontoDeProbabilidad(monto, j, jugada)
}
case class ApuestaCompuesta(apuestas: Seq[(Suceso, Double)]) extends Apuesta {
  def Ganada[T<:Jugada](j: Juego[T], res: T): Boolean =
    apuestas.exists((par) => j.Sucedio(par._1, res))
  def Ganancias[T<:Jugada](j: Juego[T], res: T) =
    apuestas.filter((par) => j.Sucedio(par._1, res)).map((apuesta) => 
      MontoDeProbabilidad(apuesta._2, j, apuesta._1)).sum
}