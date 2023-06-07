package com.mpa.service;

import com.mpa.repository.MTSTariffRepository;
import com.mpa.domain.webresource.mts.model.MTSMobileTariff;
import com.mpa.domain.webresource.mts.MTSParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MTSTariffService {

    private final MTSParser mtsParser;
    private final MTSTariffRepository mtsTariffRepository;

    public MTSTariffService(MTSParser mtsParser, MTSTariffRepository mtsTariffRepository) {
        this.mtsParser = mtsParser;
        this.mtsTariffRepository = mtsTariffRepository;
    }

    public List<MTSMobileTariff> getMTSMobileTariffsFromWeb() {
        List<MTSMobileTariff> tariffs = new ArrayList<>();
        try {
            tariffs = mtsParser.parseMobileTariffs();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tariffs;
    }

    public List<MTSMobileTariff> getMTSMobileTariffsFromDb() {
        return mtsTariffRepository.listAll();
    }

    @Transactional
    public void saveAll(List<MTSMobileTariff> tariffs) {
        mtsTariffRepository.persist(tariffs);
    }
}
