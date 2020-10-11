package br.com.sgm.spatialdataprocessor.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import br.com.sgm.spatialdataprocessor.client.exception.ApiCallException;
import br.com.sgm.spatialdataprocessor.client.exception.ParsingException;
import br.com.sgm.spatialdataprocessor.client.response.Taxes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Classe client do endpoint do sistema STUR
 */
@Slf4j
@Service
public class SturClient {

    @Value("${stur-endpoint-uri:localhost:8081}")
    private String sturEndpointUri;

    public List<Taxes> getDataFromStur() throws ApiCallException, ParsingException {
        HttpClient client = HttpClient.newBuilder().build();

        final var response = executeCall(client,  URI.create(sturEndpointUri));

        final var taxes = parseResponse(response);

        return taxes;
    }

    private HttpResponse<String> executeCall(final HttpClient client, final URI uri) throws ApiCallException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            log.info(uri.toString());
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info(response.body());
            return response;
        } catch (Exception e) {
            log.error(uri.toString(), e);
            throw new ApiCallException(e);
        }
    }

    private List<Taxes> parseResponse(final HttpResponse<String> response) throws ParsingException {
        try {
            return new ObjectMapper().readValue(response.body(),  new TypeReference<List<Taxes>>(){});
        } catch (JsonProcessingException e) {
            log.error(response.body(), e);
            throw new ParsingException(e);
        }
    }
}
