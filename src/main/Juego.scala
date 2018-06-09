package main

trait Jugada extends Suceso

trait Resultado extends Suceso {
  val monto: Double
  val suceso: Suceso
}
case class Win(val monto: Double, val suceso: Suceso) extends Resultado {}
case class Lose(val suceso: Suceso) extends Resultado {
  val monto = 0.0
}

trait Juego[TipoJugada<:Jugada] {
  def Distribucion(): Distribucion[TipoJugada]
  def ResultadosPosibles() = Distribucion().SucesosPosibles()
  def Probabilidad(s: Suceso) = Distribucion().Probabilidad(s)
  def Sucedio(suceso: Suceso, resultado: TipoJugada) = suceso == resultado
  def PonerApuesta(x: Apuesta): Distribucion[Resultado] = x(this)
}