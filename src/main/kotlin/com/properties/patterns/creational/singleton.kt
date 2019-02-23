package com.properties.patterns.creational

object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print() = println("Printing with object: $this")
}

fun main() {
    println("Start")
    PrinterDriver.print()
    PrinterDriver.print()
}