package br.com.sgm.spatialdataprocessor.endpoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

/**
 * Classe que representa os impostos da região
 */
@AllArgsConstructor
public class RegionTaxes {

    private static final String REGION = "region";
    private static final String TAXES = "taxes";

    @JsonProperty(REGION)
    private Region region;

    @JsonProperty(TAXES)
    private TotalTax taxes;

}
