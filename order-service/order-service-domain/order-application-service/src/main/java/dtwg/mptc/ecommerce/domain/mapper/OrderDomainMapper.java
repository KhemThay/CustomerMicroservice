package dtwg.mptc.ecommerce.domain.mapper;

import dtwg.mptc.ecommerce.domain.dto.CommandOrderItem;
import dtwg.mptc.ecommerce.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {

    @Mapping(source = "customerId",target = "customerId.value")
    @Mapping(source = "businessId",target = "businessId.value")
    @Mapping(source = "price",target = "price.amount")
    Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand);

    @Mapping(source = "productId"  , target = "product.id.value")
    @Mapping(source = "price",target = "price.amount")
    @Mapping(source = "subTotal",target = "subTotal.amount")
    OrderItem commandOrderItemToOrderItem(CommandOrderItem commandOrderItem);
}
