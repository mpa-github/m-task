package com.mpa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpa.service.MTSTariffService;
import com.mpa.domain.webresource.mts.model.MTSMobileTariff;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tariff/mts/mobile")
public class TariffController {

    private final MTSTariffService mtsTariffService;
    private final ObjectMapper objectMapper;

    public TariffController(MTSTariffService mtsTariffService, ObjectMapper objectMapper) {
        this.mtsTariffService = mtsTariffService;
        this.objectMapper = objectMapper;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/web")
    public String getMobileTariffsFromWeb() throws JsonProcessingException {
        List<MTSMobileTariff> tariffs = mtsTariffService.getMTSMobileTariffsFromWeb();
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        return objectMapper.writer(prettyPrinter).writeValueAsString(tariffs);
    }

    /*@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/db")
    public String getMobileTariffsFromDB() throws JsonProcessingException {
        List<MTSMobileTariff> tariffs = mtsTariffService.getMTSMobileTariffsFromDb();
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        return objectMapper.writer(prettyPrinter).writeValueAsString(tariffs);
    }*/

    /*@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/db")
    public String saveMobileTariffs() {
        List<MTSMobileTariff> tariffs = mtsTariffService.getMTSMobileTariffsFromWeb();
        mtsTariffService.saveAll(tariffs);
        return "Success!";
    }*/
}
