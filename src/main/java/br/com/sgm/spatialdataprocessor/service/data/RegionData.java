package br.com.sgm.spatialdataprocessor.service.data;

import br.com.sgm.spatialdataprocessor.endpoint.response.TaxType;
import lombok.Getter;

/**
 * Enum de dados da região
 */
@Getter
public enum RegionData {

    CNTR("CNTR", "Centro", 3000L, TaxType.IPTU),
    LEST("LEST", "Leste", 2000L, TaxType.IPTU),
    OEST("OEST", "Oeste", 1000L, TaxType.IPTU),
    NORT("NORT", "Norte", 500L, TaxType.IPTU),
    SUL("SUL", "Sul", 800L, TaxType.IPTU),
    RUR1("RUR1", "Rural 1", 500L, TaxType.ITR),
    RUR2("RUR2", "Rural 2", 200L, TaxType.ITR),
    RUR3("RUR3", "Rural 3", 300L, TaxType.ITR),
    RUR4("RUR4", "Rural 4", 400L, TaxType.ITR),
    RUR5("RUR5", "Rural 5", 500L, TaxType.ITR);

    private String code;
    private String name;
    private long population;
    private TaxType taxType;

    RegionData(final String code, final String name, final long population, final TaxType taxType) {
        this.code = code;
        this.name = name;
        this.population = population;
        this.taxType = taxType;
    }
}
