package dtwg.mptc.ecommerce.customer.restapi.mapper;

import dtwg.mptc.ecommerce.customer.domain.dto.CreateCustomerCommand;
import dtwg.mptc.ecommerce.customer.domain.dto.CreateCustomerResult;
import dtwg.mptc.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import dtwg.mptc.ecommerce.customer.domain.dto.UpdateCustomerResult;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerCreateRequest;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerCreateResponse;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerUpdateRequest;
import dtwg.mptc.ecommerce.customer.restapi.dto.CustomerUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);
    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    // customerId comes from the path, the rest from the request body
    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);
    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);
}
