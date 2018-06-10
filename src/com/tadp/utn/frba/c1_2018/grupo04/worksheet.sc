package com.tadp.utn.frba.c1_2018.grupo04

object worksheet {
  val apuesta1 = ApuestaSimple(Cara(), 10.0)      //> apuesta1  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaSimple = ApuestaSimple(
                                                  //| Cara(),10.0)
  val apuesta2 = ApuestaSimple(Numero(0), 15.0)   //> apuesta2  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaSimple = ApuestaSimple(
                                                  //| Numero(0),15.0)
  val apuestaCompuesta = ApuestaCompuesta(Seq(apuesta1, apuesta2))
                                                  //> apuestaCompuesta  : com.tadp.utn.frba.c1_2018.grupo04.ApuestaCompuesta = Apu
                                                  //| estaCompuesta(List(ApuestaSimple(Cara(),10.0), ApuestaSimple(Numero(0),15.0)
                                                  //| ))
}