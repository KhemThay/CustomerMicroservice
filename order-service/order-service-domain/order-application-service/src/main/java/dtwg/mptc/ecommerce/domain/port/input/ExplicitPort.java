package dtwg.mptc.ecommerce.domain.port.input;

import dtwg.mptc.ecommerce.domain.dto.CreateOrderCommand;

public interface ExplicitPort {

    void execute(CreateOrderCommand createOrderCommand);

}
