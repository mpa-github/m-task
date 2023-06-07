package com.mpa.domain.webresource.mts;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@ApplicationScoped
public class MTSPageCrawler {

    private final static String PROTOCOL = "https://";
    private final static String SPB_CITY_PATH_PREFIX = "spb";
    private final static String BASE_PATH_TO_PARSE = ".mts.ru/personal/mobilnaya-svyaz/tarifi/vse-tarifi";
    private final static String TARIFFS_JSON_PREFIX = "window.globalSettings.tariffs = ";
    private final static String EMPTY_STRING = "";

    public String getTariffsSettingsJsonString() throws IOException {
        String url = buildUrl(SPB_CITY_PATH_PREFIX);
        Document document = Jsoup.connect(url).timeout(10_000).get();
        Elements scripts = document.getElementsByTag("script");

        return scripts.stream()
            .filter(script -> script.childNodeSize() > 0)
            .map(script -> script.childNode(0).toString())
            .map(String::trim)
            .filter(text -> text.startsWith(TARIFFS_JSON_PREFIX))
            .map(text -> text.substring(TARIFFS_JSON_PREFIX.length(), text.length() - 1))
            .findFirst()
            .orElse(EMPTY_STRING);
    }

    private String buildUrl(String cityPath) {
        return PROTOCOL + cityPath + BASE_PATH_TO_PARSE;
    }
}
