package dtwg.mptc.ecommerce.order.persistence.mapper;

import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.entity.OrderItem;
import dtwg.mptc.ecommerce.domain.valueobject.StreetAddress;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderAddressEntity;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderEntity;
import dtwg.mptc.ecommerce.order.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "businessId.value", target = "businessId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "trackingId.value", target = "trackingId")
    @Mapping(source = "deliveryAddress", target = "orderAddress")
    @Mapping(source = "failureMessages", target = "failureMessages", qualifiedByName = "mapFailureMessages")
    OrderEntity orderToOrderEntity(Order order);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    OrderAddressEntity deliveryAddressToOrderAddressEntity(StreetAddress streetAddress);

//    @Named("mapFailureMessages")
//    default String mapFailureMessages(List<String> failureMessages) {
//        return String.join(",", failureMessages);
//    }

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
    @Mapping(target = "failureMessages", source = "failureMessages", qualifiedByName = "mapFailureMessagesToList")
    Order orderEntityToOrder(OrderEntity orderEntity);


    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "product.id.value", source = "productId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "subTotal.amount", source = "subTotal")
    OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity);

//    @Named("mapFailureMessagesToList")
//    default List<String> mapFailureMessagesToList(String failureMessages) {
//        return Arrays.stream(failureMessages.split(",")).toList();
//    }


    @Named("mapFailureMessages")
    default String mapFailureMessages(List<String> failureMessages) {
        if (failureMessages == null || failureMessages.isEmpty()) {
            return null;
        }
        return String.join(",", failureMessages);
    }

    @Named("mapFailureMessagesToList")
    default List<String> mapFailureMessagesToList(String failureMessages) {
        if (failureMessages == null || failureMessages.isBlank()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.stream(failureMessages.split(",")).toList());
    }


}
