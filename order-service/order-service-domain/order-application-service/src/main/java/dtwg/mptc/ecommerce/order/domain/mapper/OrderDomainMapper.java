package dtwg.mptc.ecommerce.order.domain.mapper;

import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.entity.OrderItem;
import dtwg.mptc.ecommerce.domain.valueobject.StreetAddress;
import dtwg.mptc.ecommerce.order.domain.dto.CommandOrderAddress;
import dtwg.mptc.ecommerce.order.domain.dto.CommandOrderItem;
import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {

    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "businessId", target = "businessId.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "deliveryAddress", target = "streetAddress")
    Order creatOrderCommandToOrder(CreateOrderCommand createOrderCommand);

    StreetAddress commandOrderAddressToStreetAddress(CommandOrderAddress commandOrderAddress);

    @Mapping(source = "productId", target = "product.id.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "subTotal", target = "subTotal.amount")
    OrderItem commandOrderItemToOrder(CommandOrderItem commandOrderItem);
}
