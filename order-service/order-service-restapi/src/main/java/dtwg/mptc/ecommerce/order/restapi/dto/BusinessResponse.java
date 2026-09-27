package dtwg.mptc.ecommerce.order.restapi.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record BusinessResponse(
        UUID id,
        boolean active,
        List<ProductResponse> products
) {
}
