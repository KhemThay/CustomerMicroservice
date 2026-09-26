package dtwg.mptc.ecommerce.domain.entity;

import dtwg.mptc.ecommerce.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId>{


    private final String username;
    private final String familyName;
    private final String giveName;

    private Customer(Builder builder) {
        super.setId(builder.id);
        username = builder.username;
        familyName = builder.familyName;
        giveName = builder.giveName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CustomerId id;
        private String username;
        private String familyName;
        private String giveName;

        private Builder() {
        }


        public Builder id(CustomerId val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder familyName(String val) {
            familyName = val;
            return this;
        }

        public Builder giveName(String val) {
            giveName = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
