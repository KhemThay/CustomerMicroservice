package dtwg.mptc.ecommerce.customer.domain.exception;

import dtwg.mptc.ecommerce.exception.CustomerDomainException;

public class CustomerNotFoundException extends CustomerDomainException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
