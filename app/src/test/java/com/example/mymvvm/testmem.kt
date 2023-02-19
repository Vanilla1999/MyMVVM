package com.example.mymvvm

data class Mem(val a:Int){

    init {

    }
}
class Lola private constructor(private val a: Int){

}
fun main(){
    val a:Lola = Lola(5)
    var b = Mem(4)
    b.a=5

}