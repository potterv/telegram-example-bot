package ru.devpotter.bot

import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
//import org.telegram.telegrambots.meta.api.objects

@Service
class TelegramBotExample : TelegramLongPollingBot() {

//    init {
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
                    messageTextbot.startsWith("кнопка")-> "Вы нажали кнопку"
//                    messageTextbot.startsWith("Hello")-> "Вы нажали кнопку inlineButton"
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
        responseMessage.replyMarkup = getReplayMurkup(
            listOf(
                listOf("Обратнаясвязь", "О боте"),
                listOf("кнопка 3", "кнопка 4")
            )
        )
//        responseMessage.replyMarkup = getInlineKeyboardMurkup(
//            listOf(
//                listOf(InlineKeyboardButton("1"), InlineKeyboardButton("2")),
//                listOf(InlineKeyboardButton("кнопка 5"), InlineKeyboardButton("кнопка 6"))
//            )
//        )

//        responseMessage.replyMarkup =InlineKeyboardMarkup(listOf(listOf(InlineKeyboardButton("General Kenobi"))))
        execute(responseMessage)
    }

    private fun getReplayMurkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val murkup = ReplyKeyboardMarkup()
        murkup.keyboard = allButtons.map{rowButtons ->
            val row = KeyboardRow()
        rowButtons.forEach{rowButton -> row.add(rowButton.toString())}
        row
        }
        return murkup
    }

    private fun getInlineKeyboardMurkup(allButtons: List<List<InlineKeyboardButton>>):InlineKeyboardMarkup{
        val inlineMurkup = InlineKeyboardMarkup(allButtons)

        return inlineMurkup
    }

}
