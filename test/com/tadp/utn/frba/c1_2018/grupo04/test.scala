package com.tadp.utn.frba.c1_2018.grupo04
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

@Test
class Apuesta_Test {

  private var apuesta1: ApuestaSimple = _
  private var apuesta2: ApuestaSimple = _
  private var apuestaCompuesta1: ApuestaCompuesta = _
  @Before
  def setup() = {
    apuesta1 = ApuestaSimple(Cara(), 10.0)
    apuesta2 = ApuestaSimple(Numero(0), 15.0)
    apuestaCompuesta1 = ApuestaCompuesta(Seq(apuesta1, apuesta2))
  }

  @Test
  def ApuestaSimple1_test() = {
    assertEquals(ResultadoArbol(ResultadoFinal(0.0, 50.0), ResultadoFinal(20.0, 50.0)), apuesta1(10.0))
  }
  @Test
  def ApuestaSimple2_test() = {
    val ResultadoArbol(ResultadoFinal(m1, p1), ResultadoFinal(m2, p2)) = apuesta2(15.0)
    assertEquals(m1, 0.0, 0.05)
    assertEquals(m2, 540.0, 0.05)
    assertEquals(p1, 100.0 - Ruleta.Probabilidad(Numero(0)), 0.05)
    assertEquals(p2, Ruleta.Probabilidad(Numero(0)), 0.05)
  }
  @Test
  def ApuestaCompuesta2_test() = {
    val ResultadoArbol(ResultadoFinal(m1, p1),
      ResultadoCompuesto(ResultadoArbol(ResultadoFinal(m2, p2),
        ResultadoFinal(m3, p3)), p4)) = apuestaCompuesta1(15.0)
    assertEquals(m1, 5.0, 0.05)
    assertEquals(m2, 10.0, 0.05)
    assertEquals(m3, 550.0, 0.05)
    assertEquals(p1, Moneda.Probabilidad(Cara()), 0.05)
    assertEquals(p2, 100.0 - Ruleta.Probabilidad(Numero(0)), 0.05)
    assertEquals(p3, Ruleta.Probabilidad(Numero(0)), 0.05)
    assertEquals(p4, Moneda.Probabilidad(Cruz()), 0.05)
  }

}