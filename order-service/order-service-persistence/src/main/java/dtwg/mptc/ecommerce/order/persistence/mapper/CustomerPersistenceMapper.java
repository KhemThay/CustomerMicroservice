package dtwg.mptc.ecommerce.order.persistence.mapper;

import dtwg.mptc.ecommerce.domain.entity.Customer;
import dtwg.mptc.ecommerce.order.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "givenName", target = "giveName")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

}
