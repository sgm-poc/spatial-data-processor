package br.com.sgm.spatialdataprocessor.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.sgm.spatialdataprocessor.client.SturClient;
import br.com.sgm.spatialdataprocessor.client.exception.ApiCallException;
import br.com.sgm.spatialdataprocessor.client.exception.ParsingException;
import br.com.sgm.spatialdataprocessor.client.response.Taxes;
import br.com.sgm.spatialdataprocessor.endpoint.response.Region;
import br.com.sgm.spatialdataprocessor.endpoint.response.RegionTaxes;
import br.com.sgm.spatialdataprocessor.endpoint.response.TaxType;
import br.com.sgm.spatialdataprocessor.endpoint.response.TotalTax;
import br.com.sgm.spatialdataprocessor.service.exception.RegionTaxesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Serviço que processa requisições juntamente com dados de outros serviços
 */
@Service
@Slf4j
public class SpatialDataService {

    @Value("${stur-endpoint-uri:localhost:8081}")
    private String sturEndpointUri;

    @Autowired
    private SturClient sturClient;

    public Optional<Region> getRegion(final String regionCode) throws RegionTaxesException {

        final List<Taxes> taxes;
        try {
            taxes = sturClient.getDataFromStur();
        } catch (ApiCallException | ParsingException e) {
            e.printStackTrace();
            throw new RegionTaxesException(e);
        }

        final var filteredTax = taxes.stream().filter(tax -> tax.getRegionCode().equalsIgnoreCase(regionCode))
                .findFirst();

        if (filteredTax.isEmpty()) {
            return Optional.empty();
        }

        //TODO obter dados adicionais da região por outro serviço externo
        return Optional
                .of(Region.builder().code(filteredTax.get().getRegionCode()).name("CENTRO").habitants(100L).build());
    }

    public Optional<RegionTaxes> getRegionTaxes(final String regionCode) throws RegionTaxesException {

        final List<Taxes> taxes;
        try {
            taxes = sturClient.getDataFromStur();
        } catch (ApiCallException | ParsingException e) {
            e.printStackTrace();
            throw new RegionTaxesException(e);
        }

        final var filteredTax = taxes.stream().filter(tax -> tax.getRegionCode().equalsIgnoreCase(regionCode))
                .findFirst();

        if (filteredTax.isEmpty()) {
            return Optional.empty();
        }

        //TODO obter dados adicionais da região por outro serviço externo
        final var region = Region.builder().code(filteredTax.get().getRegionCode()).name("CENTRO").habitants(100L).build();
        final var totalTax = TotalTax.builder().amount(filteredTax.get().getAmount()).type(TaxType.IPTU).build();

        final var regionTaxes = RegionTaxes.builder().region(region).taxes(totalTax).build();

        return Optional.of(regionTaxes);
    }
}

