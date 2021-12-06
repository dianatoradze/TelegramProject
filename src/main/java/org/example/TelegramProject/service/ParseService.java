package org.example.TelegramProject.service;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseService {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM");

    public String parseDateBeginQuery(CallbackQuery callbackQuery) {
        return callbackQuery.getData().split("\\|")[2];
    }

    private Date parseDate(String date) {
        Date dateBegin = null;
        try {
            dateBegin = DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateBegin;
    }
}
