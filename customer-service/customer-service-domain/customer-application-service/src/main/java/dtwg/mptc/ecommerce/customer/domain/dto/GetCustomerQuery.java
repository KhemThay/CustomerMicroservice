package dtwg.mptc.ecommerce.customer.domain.dto;

import java.util.UUID;

public record GetCustomerQuery(
        UUID customerId
) {
}
