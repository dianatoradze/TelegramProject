package org.example.TelegramProject.parcer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Apart {
    List<Apart> apartList = new ArrayList<>();
    Parcer parcer;
    private Document document;

    public Apart() {
        connect();
    }

    //подключаемся к странице
    private void connect() {
        try {
            document = Jsoup.connect("https://www.avito.ru/ekaterinburg/kvartiry/sdam-ASgBAgICAUSSA8gQ").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //получаем название объявления
    public String getTitle() {
        return document.title();
    }

    //читаем описание
    public String getDescription() {
        Elements element = document.getElementsByClass("links-row-Gormg");
        return element.text();
    }

    //тип квартиры
    public String getApartType() {
        Elements element = document.getElementsByAttributeValue("href", "title");
        String type = String.valueOf(element.val("title"));
        type = type.replace(",", "");
        //чистим  от лишнего

        return type;
    }

    //последние объявления по дате
    public String getDate() {
        Elements elements = document.getElementsByClass("iva-item-dateInfoStep-_dkz9");

        String date = elements.text();

        return date;
    }

    //берем изображение из объявления
    public String getImage() {
        //iva-item-slider-GWoCM
        Elements elements = document.getElementsByClass("photo-slider-item-o_UGQ photo-slider-keepImageRatio-NrG6s");
        String url = elements.attr("style");
        //чистим url от лишнего

        url = url.replace("');", "");

        //чистим url от лишнего

        return url;
    }

    //адрес квартиры
    public String getAdress() {
        Elements elements = document.getElementsByClass("iva-item-developmentNameStep-n46gZ");
        return elements.text();
    }

    public String getSum() {
        Elements elements = document.getElementsByClass("iva-item-priceStep-QN8Kl");
        return elements.text();
    }

}