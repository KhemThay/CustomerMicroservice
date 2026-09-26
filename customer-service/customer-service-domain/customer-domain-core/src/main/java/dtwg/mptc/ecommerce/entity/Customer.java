package dtwg.mptc.ecommerce.entity;

import dtwg.mptc.ecommerce.domain.entity.AggregateRoot;
import dtwg.mptc.ecommerce.domain.valueobject.*;

public class Customer extends AggregateRoot<CustomerId> {

    private final String username;
    private String familyName;
    private String givenName;
    private Email email;
    private PhoneNumber phoneNumber;
    private LoyaltyTier loyaltyTier;
    private CustomerStatus status;

    private Customer(Builder builder) {
        super.setId(builder.id);
        username = builder.username;
        familyName = builder.familyName;
        givenName = builder.givenName;
        email = builder.email;
        phoneNumber = builder.phoneNumber;
        loyaltyTier = builder.loyaltyTier;
    }

    public static final class Builder {
        private CustomerId id;
        private String username;
        private String familyName;
        private String givenName;
        private Email email;
        private PhoneNumber phoneNumber;
        private LoyaltyTier loyaltyTier;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
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

        public Builder givenName(String val) {
            givenName = val;
            return this;
        }

        public Builder email(Email val) {
            email = val;
            return this;
        }

        public Builder phoneNumber(PhoneNumber val) {
            phoneNumber = val;
            return this;
        }

        public Builder loyaltyTier(LoyaltyTier val) {
            loyaltyTier = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
