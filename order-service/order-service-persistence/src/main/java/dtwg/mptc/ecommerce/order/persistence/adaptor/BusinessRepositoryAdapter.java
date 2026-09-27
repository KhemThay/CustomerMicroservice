package dtwg.mptc.ecommerce.order.persistence.adaptor;

import dtwg.mptc.ecommerce.domain.entity.Business;
import dtwg.mptc.ecommerce.order.domain.port.output.BusinessRepository;
import dtwg.mptc.ecommerce.order.persistence.entity.BusinessEntity;
import dtwg.mptc.ecommerce.order.persistence.mapper.BusinessPersistenceMapper;
import dtwg.mptc.ecommerce.order.persistence.repository.BusinessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

    private final BusinessJpaRepository businessJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;

    @Override
    public Optional<Business> findBusiness(Business business) {

        List<UUID> businessProducts = businessPersistenceMapper.businessToBusinessProducts(business);

        List<BusinessEntity> businessEntities = businessJpaRepository.findByBusinessIdAndProductIdIn
                (business.getId().value(),
                        businessProducts);

        if (businessEntities.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(businessPersistenceMapper.businessEntityToBusiness(businessEntities));
    }

}
