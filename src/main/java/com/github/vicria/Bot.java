package com.github.vicria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            String text = message.getText();
            response.setChatId("134472191")
                    .setText(text);
            SendMessage answer = new SendMessage();
            answer.setChatId(message.getChatId())
                    .setText("Твое сообщение отправлено");
            try {
                execute(response);
                execute(answer);
                log.info("Sent message \"{}\" to vicria", text);
            } catch (TelegramApiException e) {
                log.error("Failed to send message \"{}\" to vicria due to error: {}", text, e.getMessage());
            }
        }
    }

    @PostConstruct
    public void start() {
        log.info("username: {}, token: {}", username, token);
    }

}
