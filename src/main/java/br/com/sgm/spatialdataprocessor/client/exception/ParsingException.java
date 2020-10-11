package br.com.sgm.spatialdataprocessor.client.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception específica do parser
 */
public class ParsingException extends Exception {

    private static final String ERROR_MESSAGE = "Erro no parser";

    public ParsingException(final JsonProcessingException e) {
        super(ERROR_MESSAGE, e);
    }
}
