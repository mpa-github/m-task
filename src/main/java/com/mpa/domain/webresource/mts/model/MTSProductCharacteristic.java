package com.mpa.domain.webresource.mts.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class MTSProductCharacteristic extends PanacheEntity {

    private String title;
    private String value;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private MTSMobileTariff tariff;

    public MTSProductCharacteristic() {
    }

    public MTSProductCharacteristic(String title, String value, String description) {
        this.title = title;
        this.value = value;
        this.description = description;
    }

    public MTSProductCharacteristic(MTSProductCharacteristic.Builder builder) {
        this.title = builder.title;
        this.value = builder.value;
        this.description = builder.description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MTSMobileTariff getTariff() {
        return tariff;
    }

    public void setTariff(MTSMobileTariff tariff) {
        this.tariff = tariff;
    }

    public static class Builder {

        private String title;
        private String value;
        private String description;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public MTSProductCharacteristic build() {
            return new MTSProductCharacteristic(this);
        }
    }
}
