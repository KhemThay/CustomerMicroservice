package dtwg.mptc.ecommerce.customer.domain.exception;

import dtwg.mptc.ecommerce.exception.CustomerDomainException;

public class CustomerAlreadyExistsException extends CustomerDomainException {

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
