package br.com.sgm.spatialdataprocessor.endpoint;

import java.time.LocalDateTime;

import br.com.sgm.spatialdataprocessor.endpoint.response.County;
import br.com.sgm.spatialdataprocessor.service.SpatialDataService;
import br.com.sgm.spatialdataprocessor.service.exception.RegionTaxesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por prover um endpoint para obtenção de dados gerais do município
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/county")
public class CountyEndpoint {

    @Autowired
    private SpatialDataService spatialDataService;

    @GetMapping("/data")
    public ResponseEntity<County> getCountyData() throws RegionTaxesException {

        log.info("getCountyData:" + LocalDateTime.now().toString());

        final var countyData = spatialDataService.getCountyData();

        return ResponseEntity.of(countyData);
    }
}
