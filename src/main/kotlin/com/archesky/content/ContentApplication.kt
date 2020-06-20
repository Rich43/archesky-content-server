package com.archesky.content

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages=[
    "com.pynguins.content",
    "com.pynguins.auth.library.security",
    "com.pynguins.auth.library.service"
])
class ContentApplication

fun main(args: Array<String>) {
    runApplication<ContentApplication>(*args)
}
