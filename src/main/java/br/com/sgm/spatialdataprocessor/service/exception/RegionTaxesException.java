package br.com.sgm.spatialdataprocessor.service.exception;

/**
 * Exception do serviço de imposto da região
 */
public class RegionTaxesException extends Exception {

    private static final String ERROR_MESSAGE = "Erro no serviço de imposto da região";
    public RegionTaxesException(final Exception e) {
        super(ERROR_MESSAGE, e);
    }
}
