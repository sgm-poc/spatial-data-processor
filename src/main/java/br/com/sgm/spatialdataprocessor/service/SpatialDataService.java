package br.com.sgm.spatialdataprocessor.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.sgm.spatialdataprocessor.client.SturClient;
import br.com.sgm.spatialdataprocessor.client.exception.ApiCallException;
import br.com.sgm.spatialdataprocessor.client.exception.ParsingException;
import br.com.sgm.spatialdataprocessor.client.response.Taxes;
import br.com.sgm.spatialdataprocessor.endpoint.response.Region;
import br.com.sgm.spatialdataprocessor.endpoint.response.RegionList;
import br.com.sgm.spatialdataprocessor.endpoint.response.RegionTaxes;
import br.com.sgm.spatialdataprocessor.endpoint.response.TaxType;
import br.com.sgm.spatialdataprocessor.endpoint.response.TotalTax;
import br.com.sgm.spatialdataprocessor.service.data.RegionData;
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
            log.error("Erro ao obter dados do STUR", e);
            throw new RegionTaxesException(e);
        }

        final var filteredTax = taxes.stream().filter(tax -> tax.getRegionCode().equalsIgnoreCase(regionCode))
                .findFirst();

        if (filteredTax.isEmpty()) {
            return Optional.empty();
        }

        //TODO obter dados adicionais da região por outro serviço externo
        final var data = RegionData.valueOf(filteredTax.get().getRegionCode());

        return Optional
                .of(Region.builder().code(data.getCode()).name(data.getName()).population(data.getPopulation()).build());
    }

    public Optional<RegionList> getRegionList() throws RegionTaxesException {

        final List<Taxes> taxes;
        try {
            taxes = sturClient.getDataFromStur();
        } catch (ApiCallException | ParsingException e) {
            log.error("Erro ao obter dados do STUR", e);
            throw new RegionTaxesException(e);
        }

        if (taxes.isEmpty()) {
            return Optional.empty();
        }

        //TODO obter dados adicionais da região por outro serviço externo
        final var regionList = taxes.stream()
                .map(tax -> {
                    final var data = RegionData.valueOf(tax.getRegionCode());
                    return Region.builder().code(data.getCode()).name(data.getName()).population(data.getPopulation()).build();
                })
                .collect(Collectors.toList());

        return Optional.of(RegionList.builder().regionList(regionList).build());
    }

    public Optional<RegionTaxes> getRegionTaxes(final String regionCode) throws RegionTaxesException {

        final List<Taxes> taxes;
        try {
            taxes = sturClient.getDataFromStur();
        } catch (ApiCallException | ParsingException e) {
            log.error("Erro ao obter dados do STUR", e);
            throw new RegionTaxesException(e);
        }

        final var filteredTax = taxes.stream().filter(tax -> tax.getRegionCode().equalsIgnoreCase(regionCode))
                .findFirst();

        if (filteredTax.isEmpty()) {
            return Optional.empty();
        }

        //TODO obter dados adicionais da região por outro serviço externo
        final var data = RegionData.valueOf(filteredTax.get().getRegionCode());

        final var region = Region.builder().code(data.getCode()).name(data.getName()).population(data.getPopulation()).build();
        final var totalTax = TotalTax.builder().amount(filteredTax.get().getAmount()).type(data.getTaxType()).build();

        final var regionTaxes = RegionTaxes.builder().region(region).taxes(totalTax).build();

        return Optional.of(regionTaxes);
    }
}

