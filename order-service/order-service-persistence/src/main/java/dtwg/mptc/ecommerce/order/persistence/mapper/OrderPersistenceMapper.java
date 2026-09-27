package dtwg.mptc.ecommerce.order.persistence.mapper;

import dtwg.mptc.ecommerce.domain.entity.Customer;
import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.entity.OrderItem;
import dtwg.mptc.ecommerce.domain.valueobject.StreetAddress;
import dtwg.mptc.ecommerce.order.persistence.entity.CustomerEntity;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderAddressEntity;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderEntity;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "businessId.value", target = "businessId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "trackingId.value", target = "trackingId")
    @Mapping(source = "streetAddress", target = "orderAddress")
    @Mapping(source = "failureMessages", target = "failureMessages", qualifiedByName = "mapFailureMessages")
    OrderEntity orderToOrderEntity(Order order);

    //expression is used to generate a random UUID for the deliveryAddressId
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    OrderAddressEntity deliveryAddressToOrderAddressEntity(StreetAddress deliveryAddress);

    @Named("mapFailureMessages")
    default String mapFailureMessages(List<String> failureMessages) {
        if (failureMessages == null || failureMessages.isEmpty()) {
            return null;
        }
        return String.join(",", failureMessages);
    }


    // Issue Map List<OrderItem> to List<OrderItemEntity>
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "product.id.value", target = "productId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "subTotal.amount", target = "subTotal")
    OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "customerId.value", source = "customerId")
    @Mapping(target = "businessId.value", source = "businessId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "trackingId.value", source = "trackingId")
    @Mapping(target = "streetAddress", source = "orderAddress")
    @Mapping(target = "failureMessages", source = "failureMessages", qualifiedByName = "mapFailureMessagesToList")
    Order orderEntityToOrder(OrderEntity orderEntity);

    // Issue Map List<OrderItemEntity> to List<OrderItem>
    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "product.id.value", source = "productId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "subTotal.amount", source = "subTotal")
    OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity);

    @Named("mapFailureMessagesToList")
    default List<String> mapFailureMessagesToList(String failureMessages) {
        if (failureMessages == null || failureMessages.isBlank()) {
            return null;
        }
        return Arrays.stream(failureMessages.split(",")).toList();
    }

}
