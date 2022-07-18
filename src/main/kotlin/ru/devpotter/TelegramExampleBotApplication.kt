package ru.devpotter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TelegramExampleBotApplication

fun main(args: Array<String>) {
	runApplication<TelegramExampleBotApplication>(*args)
}
