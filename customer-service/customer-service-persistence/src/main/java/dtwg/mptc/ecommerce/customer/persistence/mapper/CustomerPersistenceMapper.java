package dtwg.mptc.ecommerce.customer.persistence.mapper;

import dtwg.mptc.ecommerce.customer.persistence.entity.CustomerEntity;
import dtwg.mptc.ecommerce.domain.valueobject.PhoneNumber;
import dtwg.mptc.ecommerce.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {
    @Mapping(source="id.value", target = "id")
    @Mapping(source="email.value", target = "email")
    @Mapping(source="phoneNumber.number", target = "phoneNumber")
    CustomerEntity customerToCustomerEntity(Customer customer);


    @Mapping(source="id", target = "id.value")
    @Mapping(source="email", target = "email.value")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "toPhoneNumber")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    @Named("toPhoneNumber")
    default PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : new PhoneNumber(phoneNumber);
    }
}
