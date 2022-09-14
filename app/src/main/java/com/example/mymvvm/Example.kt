package com.example.mymvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

class SampleViewModel : ViewModel() {
    fun loadData() {
        Example().launch { }
    }
}

fun task1(): Flow<String> {
    return (1..20).asFlow()
        .map { it * it * it * it * it }
        .transform {
            if (it > 20)
                if (it % 2 == 0)
                    emit(it.toString())
        }.map {
            it.plus("won")
        }.catch {
            print(it)
        }
}

fun task3(): Flow<Pair<Int, Int>> {
    return (1..20).asFlow()
        .zip((1..15).asFlow()) { a, b -> Pair(first = a, second = b) }
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    // проверить, первый + 2 + 3 и тд,
    for (i in nums.indices) {
        for (i1 in nums.indices)
            if (i1 != i)
                if (nums[i] + nums[i1] == target)
                    return intArrayOf(i, i1)
    }
    return intArrayOf(0, 0)
}

fun isPalindrome(x: Int): Boolean {
    val string = x.toString()
    var flag = true
    string.length
    for (i in 0..string.length / 2) {
        if (string[i] != string[string.length - 1 - i]) {
            flag = false
        }
    }
    return flag
}


class ListNode constructor(var value: Int = -1, var next: ListNode? = null) {
    override fun toString(): String {
        return "$value -> ${next.toString()}"
    }

    companion object {
        // a help function to generate a linked list with given values quickly, for test purpose only
        fun quickList(nodes: List<Int>): ListNode {
            val dummy = ListNode()
            nodes.reversed().forEach {
                val temp = ListNode(it)
                temp.next = dummy.next
                dummy.next = temp
            }
            return dummy.next!!
        }
    }
}

fun isPalindrome(head: ListNode): Boolean {
    if (head.next == null) {
        return true
    } else {
        var head2 = head
        var index = 0
        while (head2.next != null) {
            head2 = head2.next!!
            index += 1
        }
        var indexSave = index
        var index2 = 0
        var head4: ListNode? = null
        while (index > 0) {
            var golova = head
            index2 = indexSave - index
            while (index2 > 0) {
                golova = head.next!!
                index2 -= 1
            }
            var head3 = golova
            while (head3.next != head4)
                head3 = head3.next!!
            if (golova.value != head3.value) {
                return false
            } else {
                head4 = head3
            }
            index -= 1
        }
    }
    return true
}


fun isPalindrome2(head: ListNode): Boolean {



    var vals = arrayListOf<Int>();
    var currentNode = head;
    while (currentNode.next != null) {
        vals.add(currentNode.value);
        currentNode = currentNode.next!!;
    }
    vals.add(currentNode.value);
    for (i in 0..(vals.size - 1) / 2) {
        if (vals[i] != vals[vals.size - 1 - i]) {
            return false
        }
    }
    return true
}

// пиздец тут условий, вот.
fun romanToInt(s: String): Int {
    if (s.length < 2) {
        if (s == "I") {
            return 1
        } else return 5
    } else {
        if (s.length < 7) {
            var sum = 20
            var hvost = s.substring(1)
            var hvostLenght = hvost.length
            while (hvost.length != 1) {
                if (hvost[0].toString() == "V")
                    sum += 5
                if (hvost[0].toString() == "I")
                    sum += 1
                hvost.substring(hvostLenght - hvost.length - 1)
            }
            return sum
        }
    }
    return 0
}


fun longestCommonPrefix(strs: Array<String>): String {
    var flag = ""
    if (strs.size > 1) {
        for (i1 in 1 until strs.size) {
            var a = strs[0]
            if (flag != "") {
                a = a.dropLast(strs[0].length - flag.length)
            }
            var length = a.length
            var flex = 0
            var flag1 = ""
            while (length > 0 && strs[i1].length - flex > 0) {
                if (strs[i1][flex] == a[flex]) {
                    flag1 += a[flex]
                    flex += 1
                }
                length -= 1
            }
            if (flag1 == "") return ""
            flag = flag1
        }
    } else {
        return strs[0]
    }
    return flag
}




fun main() {
    longestCommonPrefix(arrayOf("flower", "flow", "flight"))
//    val list = ListNode.quickList(listOf(1, 2, 3, 2, 1))
//    isPalindrome(list)
//    isPalindrome2(list)
//    list.toString()
//    isPalindrome(121)
    print(" KOk")
    twoSum(intArrayOf(3, 2, 4), 6)
    print(" KOk")

    runBlocking() {
        //   useAppContext1()
//        val flow1 = (1..3).asFlow()
//            .onEach {  println("Берем $it") }
//            .transform { emit(it) }
//            .flatMapConcat { i -> secondFlow(i) }.flowOn(Dispatchers.IO)
//            .catch {  }
//            .collect { println("$it") }
//task1().collect {
//    println(it)
//}


        task3().collect {
            println(it.first.toString() + " " + it.second.toString())
        }
        print(" KOk")
//        val job =  launch  {
//            try {
////                val fact  = async(Dispatchers.IO) {   5 }
////                val img  =  async(Dispatchers.IO) {throw Exception()}
//                val sum = flex() + flex1()
//                println(sum)
//            } catch (e: CancellationException) {
//                Log.d("CoroutineExaption", e.toString())
//            } catch (e: Exception) {
//                Log.d("Exaption", e.toString())
//            }
//        }

    }
}

fun secondFlow(i1: Int): Flow<Int> {
    return flow {
        println(" First" + "$i1")
        emit(1 + i1)
        delay(100)
        println(" Second" + 1 + i1)
    }
}

suspend fun flex(): Int {
    delay(500)
    return 5
}

suspend fun flex1(): Int {
    return 5
}

fun useAppContext() {


    runBlocking {
        val flow = (1..10).asFlow().map { it * it }
            .transform {
                emit(it)
            }
    }
}

fun firstFlow(): Flow<Int> {
    return flow {
        for (i in 1..10)
            emit(i)
    }
}


fun useAppContext1() {
    runBlocking {
        val flow = (1..10).asFlow().map { it * it }
            .transform {
                emit(it)
            }
        // обьеденение flow
//        val flowplus = firstFlow().
//        zip(secondFlow()){a,b ->a+b}.
//        collect { println(it) }
        //есть еще странный combain

//        flowOf("A", "B", "C")
//            .onEach  { println("1$it") }
//            .collect { println("2$it") }
//        println("")
//        flowOf("A", "B", "C")
//            .onEach  { println("1$it") }
//            .buffer()  // <--------------- buffer between onEach and collect
//            .collect { println("2$it") }
        //flat map последовательно два flow идет ы


    }
}


private class WorkException(msg: String) : RuntimeException(msg) {}

private suspend fun doWork(iteration: Int): Int {
    delay(100)
    println("working on $iteration")
    return if (iteration == 0) throw WorkException("work failed") else 0
}

class Example() : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + CoroutineName("Flex")
}