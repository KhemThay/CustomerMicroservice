package dtwg.mptc.ecommerce.domain.dto;

public record CommandOrderAddress(

        String street,
        String postalCode,
        String city
) {

}
