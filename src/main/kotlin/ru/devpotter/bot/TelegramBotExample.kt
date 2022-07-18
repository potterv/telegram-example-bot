package ru.devpotter.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.BotOptions

@Service
class TelegramBotExample : TelegramLongPollingBot() {

//init {
//    options.proxyType= DefaultBotOptions.ProxyType.HTTP
//    options.proxyHost="10.92.192.50"
//    options.proxyPort = 3128
//
//}
    //    @Value("\${telegram.botName}")
    private val botName: String = "PotterVV_bot"

//    @Value("\${telegram.token}")
    private val token: String = "5494654640:AAFFEi9uedux-PjOvRcs0gxqP36Hc8i7Igc"


    override fun getBotToken(): String = token


    override fun getBotUsername(): String = botName

    override fun onUpdateReceived(update: Update?) {


        if (update!!.hasMessage()) {
            val message = update.message
            val chatId = message.chatId
            val responseText = if (message.hasText()) {
                val messageTextbot = message.text
                when {
                    messageTextbot == "/start" -> "Добро пожаловать!"
                    else -> "Вы написали: *$messageTextbot*"
                }
            } else {
                "Я понимаю только текст"
            }
            sendNotification(chatId, responseText)
        }
    }

    private fun sendNotification(chatId: Long?, responseText: String) {
        val responseMessage = SendMessage(chatId.toString(), responseText)
        responseMessage.enableMarkdownV2(true)
        execute(responseMessage)
    }

}