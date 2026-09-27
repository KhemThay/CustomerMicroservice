package dtwg.mptc.ecommerce.order.domain.usecase;

import dtwg.mptc.ecommerce.domain.entity.Business;
import dtwg.mptc.ecommerce.domain.entity.Order;
import dtwg.mptc.ecommerce.domain.entity.Product;
import dtwg.mptc.ecommerce.domain.event.OrderCreatedEvent;
import dtwg.mptc.ecommerce.domain.exception.OrderDomainException;
import dtwg.mptc.ecommerce.domain.service.OrderDomainService;
import dtwg.mptc.ecommerce.domain.valueobject.BusinessId;
import dtwg.mptc.ecommerce.domain.valueobject.Money;
import dtwg.mptc.ecommerce.domain.valueobject.ProductId;
import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import dtwg.mptc.ecommerce.order.domain.mapper.OrderDomainMapper;
import dtwg.mptc.ecommerce.order.domain.port.output.BusinessRepository;
import dtwg.mptc.ecommerce.order.domain.port.output.CustomerRepository;
import dtwg.mptc.ecommerce.order.domain.port.output.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {
// CreateOrderUseCase is domain call secondary for database job

    private final OrderDomainService orderDomainService;
    private final OrderDomainMapper orderDomainMapper;

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CreateOrderUseCase(OrderDomainService orderDomainService, OrderDomainMapper orderDomainMapper, OrderRepository orderRepository, CustomerRepository customerRepository, BusinessRepository businessRepository) {
        this.orderDomainService = orderDomainService;
        this.orderDomainMapper = orderDomainMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.businessRepository = businessRepository;
    }

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand){
        log.info("executing createOrderUseCase:{}", createOrderCommand);

        //validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException(
                        "Customer not found with ID:"+
                                createOrderCommand.customerId()));

        //validate business
        List<Product> products = createOrderCommand.items().stream()
                .map(commandOrderItem -> Product.builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build())
                .toList();

        Business business = Business.builder()
                .id(new BusinessId(createOrderCommand.businessId()))
                .products(products)
                .build();

        business = businessRepository.findBusiness(business)
                .orElseThrow(() -> new OrderDomainException("Business not found with ID:"+ createOrderCommand.businessId()));

        log.info("Found business: {}", business);

        //Invoke order domain logic
        Order order = orderDomainMapper.creatOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent =orderDomainService.validateAndInitiateOrder(order, business);
        log.info("Order created event: {}", orderCreatedEvent.getOrder().getId());

        //save order into database
        Order savedOrder = orderRepository.saveOrder(order);
        if (savedOrder == null){
            throw new OrderDomainException("Order not saved into Database");
        }

        return new CreateOrderResult(savedOrder.getId().value());

    }

}