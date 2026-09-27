package dtwg.mptc.ecommerce.customer.restapi.controller;


import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerCreateRequest;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerCreateResponse;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerUpdateRequest;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerUpdateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerCommandController {

    //TODO inject CreateCustomerUseCase, UpdateCustomerUseCase, DeactivateCustomerUseCase (customer-application-service)
    //TODO inject CustomerWebMapper once it is a MapStruct mapper

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCreateResponse createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest){
        //TODO: map request -> CreateCustomerCommand, call createCustomerUseCase.execute(), map result -> response
        return new CustomerCreateResponse(new UUID(1,1));
    }

    @PutMapping("/{customerId}")
    public CustomerUpdateResponse updateCustomer(@PathVariable UUID customerId,
                                                 @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        //TODO: map customerId + request -> UpdateCustomerCommand, call updateCustomerUseCase.execute(), map result -> response
        return new CustomerUpdateResponse(customerId);
    }

    @PatchMapping("/{customerId}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateCustomer(@PathVariable UUID customerId){
        //TODO: call deactivateCustomerUseCase.execute(customerId)
    }

}
