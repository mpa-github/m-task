package com.mpa.domain.webresource.mts.builder;

import com.mpa.domain.webresource.mts.model.MTSMobileTariff;
import com.mpa.domain.webresource.mts.model.MTSProductCharacteristic;
import com.mpa.domain.webresource.mts.model.MTSSubscription;

import java.util.List;

public interface MTSTariffBuilder {

    MTSTariffBuilder setCity(String city);
    MTSTariffBuilder setTariffType(String tariffType);
    MTSTariffBuilder setTitle(String title);
    MTSTariffBuilder setDescription(String description);
    MTSTariffBuilder setProductCharacteristics(List<MTSProductCharacteristic> productCharacteristics);
    MTSTariffBuilder setBenefitsDescription(String benefitsDescription);
    MTSTariffBuilder setSubscriptions(List<MTSSubscription> subscriptions);
    MTSMobileTariff build();
}
