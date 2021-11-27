package org.example.TelegramProject.parcer;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parcer {
    protected static final String PRICE_PATTERN = "\\b([0-9,]*)\\b";
    protected static final String CIAN_LINK_PREFIX = "https://ekb.cian.ru";

    protected double parseSum(String price) {
        try {
            Pattern pattern = Pattern.compile(PRICE_PATTERN, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(price);
            return matcher.find() ? NumberFormat.getNumberInstance(Locale.getDefault()).parse(matcher.group()).doubleValue() : 0;
        } catch (Exception ex) {
            System.out.println("exception [parsePrice] " + ex.getMessage());
            return 0;
        }
    }

}
