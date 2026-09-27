package dtwg.mptc.ecommerce.order.restapi.mapper;

import dtwg.mptc.ecommerce.domain.entity.Business;
import dtwg.mptc.ecommerce.domain.entity.Product;
import dtwg.mptc.ecommerce.order.restapi.dto.BusinessResponse;
import dtwg.mptc.ecommerce.order.restapi.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessWebMapper {

    @Mapping(source = "id.value", target = "id")
    BusinessResponse businessToBusinessResponse(Business business);

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "price.amount", target = "price")
    ProductResponse productToProductResponse(Product product);

}