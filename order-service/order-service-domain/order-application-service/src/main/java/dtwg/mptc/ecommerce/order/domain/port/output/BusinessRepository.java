package dtwg.mptc.ecommerce.order.domain.port.output;

import dtwg.mptc.ecommerce.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findBusiness(Business business);
}
