/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.06.2024
 * TIME:10:01
 */
package com.example.group_project_telegram_bot.config;

import com.example.group_project_telegram_bot.service.Telegram_Bot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@Slf4j
@Component
public class BotInitializer {
    public final Telegram_Bot bot;

    public BotInitializer(Telegram_Bot bot) {
        this.bot = bot;
    }
    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}

