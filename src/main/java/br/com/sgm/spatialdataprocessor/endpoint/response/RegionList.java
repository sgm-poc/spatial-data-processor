package br.com.sgm.spatialdataprocessor.endpoint.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * Lista de regiões
 */
@Builder
public class RegionList {

    private static final String REGION_LIST = "regionList";

    @JsonProperty(REGION_LIST)
    private List<Region> regionList;
}
