package main

case class A() extends Suceso
case class B() extends Suceso
object worksheet {
	val d = Equiprobable(List(A(), B()))      //> d  : main.Equiprobable[Product with Serializable with main.Suceso] = Equipro
                                                  //| bable(List(A(), B()))
	val d2 = Ponderada(List( (A(), 70.0), (B(), 30.0) ))
                                                  //> d2  : main.Ponderada[Product with Serializable with main.Suceso] = Ponderada
                                                  //| (List((A(),70.0), (B(),30.0)))
	val dd = Equiprobable(List(d, d2))        //> dd  : main.Equiprobable[main.Distribucion[Product with Serializable with mai
                                                  //| n.Suceso] with Product with Serializable] = Equiprobable(List(Equiprobable(L
                                                  //| ist(A(), B())), Ponderada(List((A(),70.0), (B(),30.0)))))
	val apuesta1 = ApuestaSimple(Cara(), 100) //> apuesta1  : main.ApuestaSimple = ApuestaSimple(Cara(),100.0)
	val apuesta2 = ApuestaCompuesta(Seq((Cruz(), 100), (Cara(), 100)))
                                                  //> apuesta2  : main.ApuestaCompuesta = ApuestaCompuesta(List((Cruz(),100.0), (C
                                                  //| ara(),100.0)))
	Moneda.PonerApuesta(apuesta1)             //> res0: main.Distribucion[main.Resultado] = Ponderada(List((Win(200.0,Cara()),
                                                  //| 50.0), (Lose(Cruz()),50.0)))
	Moneda.PonerApuesta(apuesta2)             //> res1: main.Distribucion[main.Resultado] = Ponderada(List((Win(200.0,Cara()),
                                                  //| 50.0), (Win(200.0,Cruz()),50.0)))
}