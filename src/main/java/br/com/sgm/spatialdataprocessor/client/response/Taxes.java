package br.com.sgm.spatialdataprocessor.client.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Impostos
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Taxes {

    private static final String AMOUNT = "amount";
    private static final String REGION_CODE = "region_code";

    @JsonProperty(AMOUNT)
    private BigDecimal amount;

    @JsonProperty(REGION_CODE)
    private String regionCode;
}
