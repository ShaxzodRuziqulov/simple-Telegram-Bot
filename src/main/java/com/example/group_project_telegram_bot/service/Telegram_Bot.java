/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:08.06.2024
 * TIME:18:41
 */
package com.example.group_project_telegram_bot.service;

import com.example.group_project_telegram_bot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Telegram_Bot extends TelegramLongPollingBot {
    private static final String HELP_TEXT = """
            This bot is created to demonstrate Spring capabilities.

            You can execute commands from the main menu on the left or by typing a command:

            Type /start to see a welcome message

            Type /mydata to see data stored about yourself

            Type /help to see this message again""";

    public final BotConfig botConfig;

    @Autowired
    public Telegram_Bot(BotConfig botConfig) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "get a welcome message"));
        commandList.add(new BotCommand("/mydata", "get your data stored"));
        commandList.add(new BotCommand("/deletedata", "delete my data"));
        commandList.add(new BotCommand("/help", "info how to use this bot"));
        commandList.add(new BotCommand("/settings", "set your preferences"));
        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    try {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized");
                    break;

            }
        }
    }

    private void startCommandReceived(Long chatId, String name) throws TelegramApiException {
        String answer = "Hi " + name + " nice to meet you! ";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(textToSend);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error setting bots command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    public Telegram_Bot(String botToken, BotConfig botConfig) {
        super(botToken);
        this.botConfig = botConfig;
    }


}
