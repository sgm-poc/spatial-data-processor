package br.com.sgm.spatialdataprocessor.endpoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Classe que representa a região para resposta do endpoint
 */
@Builder
public class Region {

    private static final String CODE = "code";

    private static final String NAME = "name";

    private static final String HABITANTS = "habitants";

    @JsonProperty(CODE)
    private String code;

    @JsonProperty(NAME)
    private String name;

    @JsonProperty(HABITANTS)
    private Long habitants;

}
