package com.example.mymoxy

fun main() {
    val victoryEkzemplyar = Victory(23)
    val a :Int
    var a1:Int
//    val chelovek = Chelovek(23)

}

//class Victory2():Chelovek2{
//    private fun perevarit(){
//        sdlkf
//    }
//    override fun eat() {
//        perevarit()
//
//        TODO("Not yet implemented")
//    }
//
//    override fun kakt(): Int {
//        TODO("Not yet implemented")
//    }
//}

interface Chelovek2{
    fun eat()
    fun kakt():Int
}

abstract class Chelovek(age: Int) {

    init {
        live(age)
    }

    abstract fun flex(age: Int)

    private fun live(age: Int) {
        flex(age)
    }
}

class Victory(age: Int) : Chelovek(age) {

    override fun flex(age: Int) {
        println("Я вика и я живу уже $age")
    }

}