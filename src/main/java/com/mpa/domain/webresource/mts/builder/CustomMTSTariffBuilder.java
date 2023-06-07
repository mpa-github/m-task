package com.mpa.domain.webresource.mts.builder;

import com.mpa.domain.webresource.mts.model.MTSMobileTariff;
import com.mpa.domain.webresource.mts.model.MTSProductCharacteristic;
import com.mpa.domain.webresource.mts.model.MTSSubscription;

import java.util.List;

public class CustomMTSTariffBuilder implements MTSTariffBuilder {

    private String city;
    private String tariffType;
    private String title;
    private String description;
    private List<MTSProductCharacteristic> productCharacteristics;
    private String benefitsDescription;
    private List<MTSSubscription> subscriptions;

    @Override
    public MTSTariffBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public MTSTariffBuilder setTariffType(String tariffType) {
        this.tariffType = tariffType;
        return this;
    }

    @Override
    public MTSTariffBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public MTSTariffBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public MTSTariffBuilder setProductCharacteristics(List<MTSProductCharacteristic> productCharacteristics) {
        this.productCharacteristics = productCharacteristics;
        return this;
    }

    @Override
    public MTSTariffBuilder setBenefitsDescription(String benefitsDescription) {
        this.benefitsDescription = benefitsDescription;
        return this;
    }

    @Override
    public MTSTariffBuilder setSubscriptions(List<MTSSubscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    @Override
    public MTSMobileTariff build() {
        return new MTSMobileTariff(
            this.city,
            this.tariffType,
            this.title,
            this.description,
            this.productCharacteristics,
            this.benefitsDescription,
            this.subscriptions
        );
    }
}
