package dtwg.mptc.ecommerce.order.domain.usecase;

import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {
// CreateOrderUseCase is domain call secondary for database job

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Execute CreateOrderUseCase : {}", createOrderCommand);
        return new CreateOrderResult(UUID.randomUUID());
    }


}
