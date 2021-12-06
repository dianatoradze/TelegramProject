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
            document = Jsoup.connect("https://www.avito.ru/ekaterinburg/kvartiry/sdam/na_dlitelnyy_srok-ASgBAgICAkSSA8gQ8AeQUg?cd=1&f=ASgBAQICAkSSA8gQ8AeQUgFAzAgkkFmOWQ&user=1").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //получаем название объявления
    public String getLink() {
        Elements links = document.select("a[href]");
        //print("\nLinks: (%d)", links.size());
        if (links.contains("квартира")) { for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
        };

        return String.valueOf(links);
    }

    //читаем описание
    public String getDescription() {
        Elements element = document.getElementsByClass("iva-item-descriptionStep-QGE8Y");
        String type = String.valueOf(element.val("title"));
        type = type.replace(",", "");
        //чистим  от лишнего

        return type;
    }

    //тип квартиры
    public String getApartType() {

        Elements elements = document.getElementsByClass("iva-item-descriptionStep-QGE8Y");
        return elements.text();

    }

    //последние объявления по дате
    public String getDate() {
        Elements elements = document.getElementsByClass("iva-item-dateInfoStep-_dkz9");

        String date = elements.text();

        return date;
    }

    //берем изображение из объявления
    public String getImage() {

        //Elements elements = document.getElementsByClass("iva-item-sliderLink-bJ9Pv");
        Elements media = document.select("[src]");
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.normalName().equals("img"))
                 print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));

        }

        return String.valueOf(media);
    }

    //адрес квартиры
    public String getAdress() {
        Elements elements = document.getElementsByClass("iva-item-titleStep-_CxvN");
        return elements.text();
    }

    public String getSum() {
        Elements elements = document.getElementsByClass("iva-item-priceStep-QN8Kl");
        return elements.text();
    }
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }


}