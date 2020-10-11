package br.com.sgm.spatialdataprocessor.client.exception;

/**
 * Exception da chamada da API
 */
public class ApiCallException extends Exception {

    private static final String ERROR_MESSAGE = "Erro na chamada da API";

    public ApiCallException(final Exception e) {
        super(ERROR_MESSAGE, e);
    }
}
