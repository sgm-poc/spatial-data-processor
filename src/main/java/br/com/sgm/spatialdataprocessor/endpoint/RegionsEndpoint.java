package br.com.sgm.spatialdataprocessor.endpoint;

import java.math.BigDecimal;

import br.com.sgm.spatialdataprocessor.endpoint.response.Region;
import br.com.sgm.spatialdataprocessor.endpoint.response.RegionTaxes;
import br.com.sgm.spatialdataprocessor.endpoint.response.TaxType;
import br.com.sgm.spatialdataprocessor.endpoint.response.TotalTax;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por prover um endpoint para manipulação de regioẽs
 */
@RestController
@RequestMapping("/regions")
public class RegionsEndpoint {

    @GetMapping("/{regionCode}")
    public ResponseEntity<Region> getRegion(@PathVariable String regionCode) {


        return ResponseEntity.ok(new Region("code", "name", 500L));
    }

    @GetMapping("/{regionCode}/taxes")
    public ResponseEntity<RegionTaxes> getRegionTaxes(@PathVariable String regionCode) {


        return ResponseEntity.ok(new RegionTaxes (new Region("code", "name", 500L), new TotalTax(BigDecimal.TEN, TaxType.IPTU)));
    }

}
