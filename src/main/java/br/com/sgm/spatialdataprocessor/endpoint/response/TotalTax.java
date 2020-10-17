package br.com.sgm.spatialdataprocessor.endpoint.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * Classe que representa o total de impostos
 */
@Builder
public class TotalTax {

    private static final String AMOUNT = "amount";
    private static final String TYPE = "type";

    @JsonProperty(AMOUNT)
    private BigDecimal amount;

    @JsonProperty(TYPE)
    private TaxType type;

}
