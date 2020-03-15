package com.pynguins.content

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ContentApplication

fun main(args: Array<String>) {
    val runner = arrayOf<Class<*>>(CrudRunner::class.java)
    SpringApplication.run(runner, args)
}
