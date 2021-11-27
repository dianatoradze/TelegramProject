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
    private Document document;

    public Apart() {
        connect();
    }

    //подключаемся к странице
    private void connect(){
        try{
            document = Jsoup.connect("https://www.avito.ru/ekaterinburg/kvartiry").get();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //получаем название объявления
    public String getTitle(){
        return document.title();
    }

    //читаем описание
    public String getDescription(){
        Elements element = document.getElementsByClass("title-info-title-text");
        return element.text();
    }

    //тип квартиры
    public String getApartType(){
        Element element = document.getElementsByClass("item-params-label").get(0);
        return element.text();
    }

    //последние объявления по дате
    public String getDate(){
        Elements elements = document.getElementsByClass("title-info-metadata-item-redesign");

        String date = elements.text();
        //чистим от текста

        return date;
    }

    //берем изображение из объявления
    public String getImage(){
        Elements elements = document.getElementsByClass("gallery-img-frame js-gallery-img-frame");
        String url = elements.attr("style");
        //чистим url от лишнего
        url = url.replace("background-image: url('", "");
        url = url.replace("');", "");
        return url;
    }

    //адрес квартиры
    public String getAdress(){
        Elements elements = document.getElementsByClass("item-address__string");
        return elements.text();
    }
}