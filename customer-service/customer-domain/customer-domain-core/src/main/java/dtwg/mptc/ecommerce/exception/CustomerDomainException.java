package dtwg.mptc.ecommerce.exception;

import dtwg.mptc.ecommerce.domain.exception.DomainException;

public class CustomerDomainException  extends DomainException {
    public CustomerDomainException(String message) {
        super(message);
    }

    public CustomerDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
