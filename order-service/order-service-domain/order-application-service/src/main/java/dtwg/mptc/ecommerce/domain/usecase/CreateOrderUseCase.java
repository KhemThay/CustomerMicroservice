package dtwg.mptc.ecommerce.domain.usecase;

import dtwg.mptc.ecommerce.domain.entity.Business;
import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.entity.Product;
import dtwg.mptc.ecommerce.domain.event.OrderCreatedEvent;
import dtwg.mptc.ecommerce.domain.exception.OrderDomainException;
import dtwg.mptc.ecommerce.domain.mapper.OrderDomainMapper;
import dtwg.mptc.ecommerce.domain.service.OrderDomainService;
import dtwg.mptc.ecommerce.domain.valueobject.BusinessId;
import dtwg.mptc.ecommerce.domain.valueobject.Money;
import dtwg.mptc.ecommerce.domain.valueobject.ProductId;
import dtwg.mptc.ecommerce.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.domain.dto.CreateOrderResult;
import dtwg.mptc.ecommerce.domain.port.output.BusinessRepository;
import dtwg.mptc.ecommerce.domain.port.output.CustomerRepository;
import dtwg.mptc.ecommerce.domain.port.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {
// CreateOrderUseCase is domain call secondary for database job

    private  final OrderDomainService orderDomainService;
    private  final OrderDomainMapper orderDomainMapper;

    private  final OrderRepository orderRepository;
    private  final CustomerRepository customerRepository;
    private  final BusinessRepository businessRepository;

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Execute CreateOrderUseCase : {}", createOrderCommand);

        //validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(()-> new OrderDomainException("Could Not Find Customer with ID:" + createOrderCommand.customerId()));

        List<Product> products = createOrderCommand.items().stream()
                .map(commandOrderItem -> Product.builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build()).toList();

        //validate business
        Business business = Business.builder()
                .id(new BusinessId(createOrderCommand.businessId()))
                .products(products)
                .build();
        business = businessRepository.findBusiness(business)
                .orElseThrow(()-> new OrderDomainException("Could Not Find Business with ID:" + createOrderCommand.businessId()));


        log.info("Founded Business : {}", business);

        //Invoke order
        Order order= orderDomainMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order,business);
        log.info("Order Created : {}",orderCreatedEvent.getOrder().getId());


    // save order into database
        Order savedOrder = orderRepository.saveOrder(order);
        if (savedOrder == null){
            throw new OrderDomainException("could not save order into database");
        }
        return new CreateOrderResult(savedOrder.getId().value());
    }


}
