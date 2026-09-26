package dtwg.mptc.ecommerce.order.domain.port.input;

import dtwg.mptc.ecommerce.order.domain.dto.CreateOrderCommand;

public interface ExplicitPort {

    void execute(CreateOrderCommand createOrderCommand);

}
