package dtwg.mptc.ecommerce.customer.restapi.exception;

import dtwg.mptc.ecommerce.exception.CustomerDomainException;
import dtwg.mptc.ecommerce.restapi.dto.RestApiErrorResponse;
import dtwg.mptc.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Every error from restapi will be handled by this class
// Validation errors (400) are inherited from GlobalExceptionHandler

@RestControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler {

    //TODO 404 handler once application layer has CustomerNotFoundException
    //TODO 409 handler once application layer has a duplicate username/email exception

    @ExceptionHandler(CustomerDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiErrorResponse<?> handleCustomerDomainException(CustomerDomainException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }
}
