package com.properties.patterns

typealias StringFormatter = (String) -> String

class Printer(private val stringFormatterStrategy: (String) -> String) {

    fun printString(string: String) {
        println(stringFormatterStrategy(string))
    }
}

val lowerCaseFormatter: StringFormatter = { it.toLowerCase() }
val upperCaseFormatter: StringFormatter = { it.toUpperCase() }

fun main() {
    val inputString = "LOREM ipsum DOLOR sit amet"

    Printer(lowerCaseFormatter).run {
        printString(inputString)
    }

    Printer(upperCaseFormatter).run {
        printString(inputString)
    }

    Printer { "Prefix: $it" }.run {
        printString(inputString)
    }
}