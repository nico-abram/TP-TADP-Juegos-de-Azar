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
    val ResultadoArbol(ResultadoFinal(m1, p1), ResultadoFinal(m2, p2), _) = apuesta2(15.0)
    assertEquals(m1, 0.0, 0.05)
    assertEquals(m2, 540.0, 0.05)
    assertEquals(p1, 100.0 - Ruleta.probabilidad(Numero(0)), 0.05)
    assertEquals(p2, Ruleta.probabilidad(Numero(0)), 0.05)
  }
  @Test
  def ApuestaCompuesta2_test() = {
    val ResultadoArbol(ResultadoFinal(m1, p1), ResultadoArbol(ResultadoFinal(m2, p2), ResultadoFinal(m3, p3), p4), _) = apuestaCompuesta1(15.0)
    assertEquals(m1, 5.0, 0.05)
    assertEquals(m2, 10.0, 0.05)
    assertEquals(m3, 550.0, 0.05)
    assertEquals(p1, Moneda.probabilidad(Cara()), 0.05)
    assertEquals(p2, 100.0 - Ruleta.probabilidad(Numero(0)), 0.05)
    assertEquals(p3, Ruleta.probabilidad(Numero(0)), 0.05)
    assertEquals(p4, Moneda.probabilidad(Cruz()), 0.05)
  }
  @Test
  def AplanarResultado_test() = {
    val x = apuestaCompuesta1(15.0).aplanar()
    assertEquals(3, x.length)
    val Some(res1) = x.find(_._1.monto == 550)
    val Some(res2) = x.find(_._1.monto == 5)
    val Some(res3) = x.find(_._1.monto == 10)
    assertEquals(res2._2, Moneda.probabilidad(Cara()), 0.05)
    assertEquals(Moneda.probabilidad(Cara()) * Ruleta.probabilidad(Numero(0)) / 100.0, res1._2, 0.05)
    assertEquals(Moneda.probabilidad(Cara()) * (100.0 - Ruleta.probabilidad(Numero(0))) / 100.0, res3._2, 0.05)
  }

}