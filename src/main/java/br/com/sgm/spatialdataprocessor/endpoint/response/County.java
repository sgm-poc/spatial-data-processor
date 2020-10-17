package br.com.sgm.spatialdataprocessor.endpoint.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * Classe de resposta para os dados do município
 */
@Builder
public class County {

    private static final String NAME = "name";
    private static final String POPULATION = "population";
    private static final String TOTAL_REGIONS = "totalRegions";
    private static final String TOTAL_TAXES = "totalTaxes";

    @JsonProperty(NAME)
    private String name;

    @JsonProperty(POPULATION)
    private long population;

    @JsonProperty(TOTAL_REGIONS)
    private long totalRegions;

    @JsonProperty(TOTAL_TAXES)
    private BigDecimal totalTaxes;

}
