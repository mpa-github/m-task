package com.mpa.domain.webresource.mts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class MTSSubscription extends PanacheEntity {

    private String title;
    private String value;
    private Double numValue;
    private String displayUnit;
    private String quotaUnit;
    private String quotaPeriod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private MTSMobileTariff tariff;

    public MTSSubscription() {
    }

    public MTSSubscription(String title,
                           String value,
                           Double numValue,
                           String displayUnit,
                           String quotaUnit,
                           String quotaPeriod) {
        this.title = title;
        this.value = value;
        this.numValue = numValue;
        this.displayUnit = displayUnit;
        this.quotaUnit = quotaUnit;
        this.quotaPeriod = quotaPeriod;
    }

    public MTSSubscription(MTSSubscription.Builder builder) {
        this.title = builder.title;
        this.value = builder.value;
        this.numValue = builder.numValue;
        this.displayUnit = builder.displayUnit;
        this.quotaUnit = builder.quotaUnit;
        this.quotaPeriod = builder.quotaPeriod;
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

    public Double getNumValue() {
        return numValue;
    }

    public void setNumValue(Double numValue) {
        this.numValue = numValue;
    }

    public String getDisplayUnit() {
        return displayUnit;
    }

    public void setDisplayUnit(String displayUnit) {
        this.displayUnit = displayUnit;
    }

    public String getQuotaUnit() {
        return quotaUnit;
    }

    public void setQuotaUnit(String quotaUnit) {
        this.quotaUnit = quotaUnit;
    }

    public String getQuotaPeriod() {
        return quotaPeriod;
    }

    public void setQuotaPeriod(String quotaPeriod) {
        this.quotaPeriod = quotaPeriod;
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
        private Double numValue;
        private String displayUnit;
        private String quotaUnit;
        private String quotaPeriod;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setNumValue(Double numValue) {
            this.numValue = numValue;
            return this;
        }

        public Builder setDisplayUnit(String displayUnit) {
            this.displayUnit = displayUnit;
            return this;
        }

        public Builder setQuotaUnit(String quotaUnit) {
            this.quotaUnit = quotaUnit;
            return this;
        }

        public Builder setQuotaPeriod(String quotaPeriod) {
            this.quotaPeriod = quotaPeriod;
            return this;
        }

        public MTSSubscription build() {
            return new MTSSubscription(this);
        }
    }
}
