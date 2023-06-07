package com.mpa.domain.webresource.mts.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class MTSMobileTariff extends PanacheEntity {

    private String city;
    private String tariffType;
    private String title;
    private String description;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tariff_id")
    private List<MTSProductCharacteristic> productCharacteristics;
    private String benefitsDescription;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tariff_id")
    private List<MTSSubscription> subscriptions;

    public MTSMobileTariff() {
    }

    public MTSMobileTariff(String city,
                           String tariffType,
                           String title,
                           String description,
                           List<MTSProductCharacteristic> productCharacteristics,
                           String benefitsDescription,
                           List<MTSSubscription> subscriptions) {
        this.city = city;
        this.tariffType = tariffType;
        this.title = title;
        this.description = description;
        this.productCharacteristics = productCharacteristics;
        this.benefitsDescription = benefitsDescription;
        this.subscriptions = subscriptions;
    }

    public MTSMobileTariff(MTSMobileTariff.Builder builder) {
        this.city = builder.city;
        this.tariffType = builder.tariffType;
        this.title = builder.title;
        this.description = builder.description;
        this.productCharacteristics = builder.productCharacteristics;
        this.benefitsDescription = builder.benefitsDescription;
        this.subscriptions = builder.subscriptions;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MTSProductCharacteristic> getProductCharacteristics() {
        return productCharacteristics;
    }

    public void setProductCharacteristics(List<MTSProductCharacteristic> productCharacteristics) {
        this.productCharacteristics = productCharacteristics;
    }

    public String getBenefitsDescription() {
        return benefitsDescription;
    }

    public void setBenefitsDescription(String benefitsDescription) {
        this.benefitsDescription = benefitsDescription;
    }

    public List<MTSSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<MTSSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "MTSMobileTariff{" +
            "city='" + city + '\'' +
            ", tariffType='" + tariffType + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", productCharacteristics=" + productCharacteristics +
            ", benefitsDescription='" + benefitsDescription + '\'' +
            ", subscriptions=" + subscriptions +
            '}';
    }

    public static class Builder {

        private String city;
        private String tariffType;
        private String title;
        private String description;
        private List<MTSProductCharacteristic> productCharacteristics;
        private String benefitsDescription;
        private List<MTSSubscription> subscriptions;

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setTariffType(String tariffType) {
            this.tariffType = tariffType;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setProductCharacteristics(List<MTSProductCharacteristic> productCharacteristics) {
            this.productCharacteristics = productCharacteristics;
            return this;
        }

        public Builder setBenefitsDescription(String benefitsDescription) {
            this.benefitsDescription = benefitsDescription;
            return this;
        }

        public Builder setSubscriptions(List<MTSSubscription> subscriptions) {
            this.subscriptions = subscriptions;
            return this;
        }

        public MTSMobileTariff build() {
            return new MTSMobileTariff(this);
        }
    }
}
