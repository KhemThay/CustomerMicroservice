package dtwg.mptc.ecommerce.order.restapi.mapper;

import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import dtwg.mptc.ecommerce.order.restapi.dto.OrderCreateRequest;
import dtwg.mptc.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

//    source = OrderCreateRequest
//    target = CreateOrderCommand

    CreateOrderCommand orderCreateRequestToCreateOrderCommand(OrderCreateRequest orderCreateRequest);

    OrderCreateResponse createOrderResultToOrderCreateResponse(CreateOrderResult createOrderResult);



}
