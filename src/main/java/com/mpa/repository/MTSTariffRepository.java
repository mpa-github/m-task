package com.mpa.repository;

import com.mpa.domain.webresource.mts.model.MTSMobileTariff;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MTSTariffRepository implements PanacheRepository<MTSMobileTariff> {
}
