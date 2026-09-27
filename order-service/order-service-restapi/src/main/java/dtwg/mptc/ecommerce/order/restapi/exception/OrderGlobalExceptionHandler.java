package dtwg.mptc.ecommerce.order.restapi.exception;


import dtwg.mptc.ecommerce.domain.exception.OrderDomainException;
import dtwg.mptc.ecommerce.persistence.business.exception.BusinessPersistenceException;
import dtwg.mptc.ecommerce.restapi.dto.RestApiErrorResponse;
import dtwg.mptc.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    //TODO : write your exception handler here
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderDomainException.class)
    public RestApiErrorResponse<?> handleOrderDomainException(OrderDomainException e) {
        return RestApiErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessPersistenceException.class)
    public RestApiErrorResponse<?> handleOrderPersistenceException(BusinessPersistenceException e) {
        return RestApiErrorResponse.builder()
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase( ))
                .message(e.getMessage())
                .build();
    }
}
