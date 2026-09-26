package dtwg.mptc.ecommerce.order.restapi.controller;


import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import dtwg.mptc.ecommerce.order.restapi.dto.OrderCreateRequest;
import dtwg.mptc.ecommerce.order.restapi.dto.OrderCreateResponse;
import dtwg.mptc.ecommerce.order.restapi.mapper.OrderWebMapper;
import dtwg.mptc.ecommerce.order.domain.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final CreateOrderUseCase createOrderUseCase;
    private  final OrderWebMapper orderWebMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(
            @Valid @RequestBody OrderCreateRequest orderCreateRequest
    ){
        CreateOrderCommand orderCommand = orderWebMapper
                .orderCreateRequestToCreateOrderCommand(orderCreateRequest);

        CreateOrderResult createOrderResult= createOrderUseCase.execute(orderCommand);

        return orderWebMapper.createOrderResultToOrderCreateResponse(createOrderResult);
    }
}
