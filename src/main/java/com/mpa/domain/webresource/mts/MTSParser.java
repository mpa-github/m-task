package com.mpa.domain.webresource.mts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpa.domain.webresource.mts.model.MTSMobileTariff;
import com.mpa.domain.webresource.mts.model.MTSProductCharacteristic;
import com.mpa.domain.webresource.mts.model.MTSSubscription;
import com.mpa.domain.webresource.mts.builder.CustomMTSTariffBuilder;
import com.mpa.utils.FileReader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class MTSParser {

    private final MTSPageCrawler crawler;
    private final ObjectMapper objectMapper;

    @Inject
    public MTSParser(MTSPageCrawler crawler, ObjectMapper objectMapper) {
        this.crawler = crawler;
        this.objectMapper = objectMapper;
    }

    public List<MTSMobileTariff> parseMobileTariffs() throws IOException {
        //ObjectMapper objectMapper = new ObjectMapper();
        //String jsonTariffsContent = fileReader.read("src/test/resources/temp.json");
        String jsonTariffsContent = crawler.getTariffsSettingsJsonString(); // TODO Handle IOException
        Map<String, Object> tariffsData = objectMapper.readValue(jsonTariffsContent, new TypeReference<>() { });

        List<MTSMobileTariff> mtsMobileTariffs = new ArrayList<>();
        Object actualTariffs = tariffsData.get("actualTariffs");
        if (actualTariffs instanceof List<?> list && list.size() > 0) {
            mtsMobileTariffs = list.stream()
                .filter(object -> object instanceof Map)
                .map(object -> (Map<?,?>) object)
                .filter(tariffs -> tariffs.get("tariffType").equals("Mobile"))
                .map(this::buildTariff)
                .collect(Collectors.toList());
        }

        return mtsMobileTariffs;
    }

    private MTSMobileTariff buildTariff(Map<?, ?> tariff) {
        List<MTSProductCharacteristic> characteristics = new ArrayList<>();
        Object productCharacteristics = tariff.get("productCharacteristics");
        if (productCharacteristics instanceof List<?> list && list.size() > 0) {
            characteristics = list.stream()
                .filter(object -> object instanceof Map)
                .map(object -> (Map<?,?>) object)
                .map(this::buildProductCharacteristic)
                .collect(Collectors.toList());
        }

        List<MTSSubscription> subscriptions = new ArrayList<>();
        if (tariff.containsKey("configurableTariffSettings")) {
            Map<?,?> configurableTariffSettings = (Map<?,?>) tariff.get("configurableTariffSettings");
            List<?> packages = (List<?>) configurableTariffSettings.get("packages");
            subscriptions = packages.stream()
                .filter(settingsPackage -> settingsPackage instanceof Map)
                .map(settingsPackage -> (Map<?,?>) settingsPackage)
                .map(settingsPackage -> (Map<?,?>) settingsPackage.get("subscriptionFee"))
                .map(this::buildSubscription)
                .collect(Collectors.toList());
        } else {
            if (tariff.containsKey("subscriptionFee")) {
                Map<?,?> subscriptionFee = (Map<?,?>) tariff.get("subscriptionFee");
                subscriptions.add(buildSubscription(subscriptionFee));
            }
        }

        String description = null;
        Object benefitsDescription = tariff.get("benefitsDescription");
        if (benefitsDescription instanceof Map<?,?> map) {
            description = (String) map.get("description");
        }

        return new CustomMTSTariffBuilder()
            .setCity("spb")
            .setTariffType((String) tariff.get("tariffType"))
            .setTitle((String) tariff.get("title"))
            .setDescription((String) tariff.get("description"))
            .setProductCharacteristics(characteristics)
            .setBenefitsDescription(description)
            .setSubscriptions(subscriptions)
            .build();
    }

    private MTSProductCharacteristic buildProductCharacteristic(Map<?, ?> characteristic) {
        return new MTSProductCharacteristic.Builder()
            .setTitle((String) characteristic.get("title"))
            .setValue((String) characteristic.get("value"))
            .setDescription((String) characteristic.get("description"))
            .build();
    }

    private MTSSubscription buildSubscription(Map<?, ?> subscription) {
        return new MTSSubscription.Builder()
            .setTitle((String) subscription.get("title"))
            .setValue((String) subscription.get("value"))
            .setNumValue((Double) subscription.get("numValue"))
            .setDisplayUnit((String) subscription.get("displayUnit"))
            .setQuotaUnit((String) subscription.get("quotaUnit"))
            .setQuotaPeriod((String) subscription.get("quotaPeriod"))
            .build();
    }
}
