package com.tadp.utn.frba.c1_2018.grupo04

object worksheet {
	Moneda.probabilidad(Cara())               //> res0: Double = 50.0
  val apuesta1 = ApuestaSimple(Cara(), 10.0)      //> apuesta1  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaSimple = ApuestaSimple(
                                                  //| Cara(),10.0)
  val apuesta2 = ApuestaSimple(Numero(0), 15.0)   //> apuesta2  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaSimple = ApuestaSimple(
                                                  //| Numero(0),15.0)
  val apuestaCompuesta = ApuestaCompuesta(Seq(apuesta1, apuesta2))
                                                  //> apuestaCompuesta  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaCompuesta = Apu
                                                  //| estaCompuesta(List(ApuestaSimple(Cara(),10.0), ApuestaSimple(Numero(0),15.0)
                                                  //| ))
  apuestaCompuesta.aplanada(15)                   //> res1: Seq[(Double, Double)] = List((500.0,0.5), (1000.0,0.48611111111111077)
                                                  //| , (55000.0,0.013888888888888888))
  apuestaCompuesta(15)                            //> res2: com.tadp.utn.frba.c1_2018.grupo04.worksheet.apuestaCompuesta.ResApuest
                                                  //| a = ResultadoNodo(100.0,ArrayBuffer(ResultadoNodo(50.0,ArrayBuffer(Resultado
                                                  //| Hoja(5.0))), ResultadoNodo(50.0,ArrayBuffer(ResultadoNodo(100.0,ArrayBuffer(
                                                  //| ResultadoNodo(97.22222222222216,ArrayBuffer(ResultadoHoja(10.0))), Resultado
                                                  //| Nodo(2.7777777777777777,ArrayBuffer(ResultadoHoja(550.0)))))))))
}