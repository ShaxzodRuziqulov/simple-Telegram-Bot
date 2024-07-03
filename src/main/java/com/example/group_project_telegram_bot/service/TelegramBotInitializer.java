/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:03.07.2024
 * TIME:10:36
 */
package com.example.group_project_telegram_bot.service;

import com.example.group_project_telegram_bot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class TelegramBotInitializer {
    public void initializeCommands(TelegramLongPollingBot bot, BotConfig botConfig) {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "get a welcome message"));
        commandList.add(new BotCommand("/mydata", "get your data stored"));
        commandList.add(new BotCommand("/deletedata", "delete my data"));
        commandList.add(new BotCommand("/help", "info how to use this bot"));
        commandList.add(new BotCommand("/settings", "set your preferences"));
        try {
            bot.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }
}
