package dtwg.mptc.ecommerce.order.restapi.mapper;

import dtwg.mptc.ecommerce.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.domain.dto.CreateOrderResult;
import dtwg.mptc.ecommerce.order.restapi.dto.OrderCreateRequest;
import dtwg.mptc.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

//    source = OrderCreateRequest
//    target = CreateOrderCommand

    @Mapping(source = "orderAddress",target = "deliveryAddress")
    CreateOrderCommand orderCreateRequestToCreateOrderCommand(OrderCreateRequest orderCreateRequest);

    OrderCreateResponse createOrderResultToOrderCreateResponse(CreateOrderResult createOrderResult);



}
