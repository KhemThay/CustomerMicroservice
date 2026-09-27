package dtwg.mptc.ecommerce.order.restapi.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponse(
        UUID id,
        String username,
        String familyName,
        String givenName
) {
}
