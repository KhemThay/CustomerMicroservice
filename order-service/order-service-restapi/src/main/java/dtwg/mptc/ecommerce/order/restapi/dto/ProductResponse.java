package dtwg.mptc.ecommerce.order.restapi.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price
) {
}
