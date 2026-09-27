package dtwg.mptc.ecommerce.domain.entity;

import dtwg.mptc.ecommerce.domain.valueobject.BusinessId;
import lombok.Getter;

import java.util.List;

@Getter
public class Business extends  AggregateRoot<BusinessId>{

    final private List<Product> products;
    final private boolean active;

    private Business(Builder builder) {
        super.setId(builder.id);
        products = builder.products;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private BusinessId id;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }


        public Builder id(BusinessId val) {
            id = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Business build() {
            return new Business(this);
        }
    }
}
