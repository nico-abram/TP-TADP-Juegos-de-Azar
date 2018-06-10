package com.tadp.utn.frba.c1_2018.grupo04

object worksheet {
  ApuestaCompuesta(Seq(ApuestaSimple(Cara(), 10.0), ApuestaSimple(Numero(0), 15.0)))(15)
                                                  //> res0: com.tadp.utn.frba.c1_2018.grupo04.Resultado = ResultadoArbol(Resultado
                                                  //| Final(5.0,50.0),ResultadoCompuesto(ResultadoArbol(ResultadoFinal(10.0,97.222
                                                  //| 22222222216),ResultadoFinal(550.0,2.7777777777777777)),50.0))
  val apuesta1 = ApuestaSimple(Cara(), 10.0)      //> apuesta1  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaSimple = ApuestaSimple(
                                                  //| Cara(),10.0)
  val apuesta2 = ApuestaSimple(Numero(0), 15.0)   //> apuesta2  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaSimple = ApuestaSimple(
                                                  //| Numero(0),15.0)
  val apuestaCompuesta1 = ApuestaCompuesta(Seq(apuesta1, apuesta2))
                                                  //> apuestaCompuesta1  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaCompuesta = Ap
                                                  //| uestaCompuesta(List(ApuestaSimple(Cara(),10.0), ApuestaSimple(Numero(0),15.0
                                                  //| )))
  apuestaCompuesta1(15.0)                         //> res1: com.tadp.utn.frba.c1_2018.grupo04.Resultado = ResultadoArbol(Resultado
                                                  //| Final(5.0,50.0),ResultadoCompuesto(ResultadoArbol(ResultadoFinal(10.0,97.222
                                                  //| 22222222216),ResultadoFinal(550.0,2.7777777777777777)),50.0))
  apuestaCompuesta1(15.0).aplanar()               //> res2: Seq[(com.tadp.utn.frba.c1_2018.grupo04.SucesoGanancia, Double)] = List
                                                  //| ((SucesoGanancia(5.0),50.0), (SucesoGanancia(10.0),48.61111111111108), (Suce
                                                  //| soGanancia(550.0),1.3888888888888888))
}