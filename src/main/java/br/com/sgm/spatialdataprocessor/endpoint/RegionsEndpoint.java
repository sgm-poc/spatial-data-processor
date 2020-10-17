package br.com.sgm.spatialdataprocessor.endpoint;

import java.time.LocalDateTime;

import br.com.sgm.spatialdataprocessor.endpoint.response.Region;
import br.com.sgm.spatialdataprocessor.endpoint.response.RegionList;
import br.com.sgm.spatialdataprocessor.endpoint.response.RegionTaxes;
import br.com.sgm.spatialdataprocessor.service.SpatialDataService;
import br.com.sgm.spatialdataprocessor.service.exception.RegionTaxesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por prover um endpoint para obtenção de dados das regiões
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/regions")
public class RegionsEndpoint {

    @Autowired
    private SpatialDataService spatialDataService;

    @GetMapping("/{regionCode}")
    public ResponseEntity<Region> getRegion(@PathVariable String regionCode) throws RegionTaxesException {

        log.info("getRegion:"+ LocalDateTime.now().toString());

        final var region = spatialDataService.getRegion(regionCode);

        return ResponseEntity.of(region);
    }

    @GetMapping("")
    public ResponseEntity<RegionList> getRegionList() throws RegionTaxesException {

        log.info("getRegionList:"+ LocalDateTime.now().toString());

        final var regionList = spatialDataService.getRegionList();

        return ResponseEntity.of(regionList);
    }

    @GetMapping("/{regionCode}/taxes")
    public ResponseEntity<RegionTaxes> getRegionTaxes(@PathVariable String regionCode) throws RegionTaxesException {
        log.info("getRegionTaxes:"+ LocalDateTime.now().toString());

        final var region = spatialDataService.getRegionTaxes(regionCode);

        return ResponseEntity.of(region);
    }

}
