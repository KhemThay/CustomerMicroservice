package dtwg.mptc.ecommerce.domain.entity;

import dtwg.mptc.ecommerce.domain.valueobject.Money;
import dtwg.mptc.ecommerce.domain.valueobject.OrderId;
import dtwg.mptc.ecommerce.domain.valueobject.OrderItemId;
import lombok.Getter;

@Getter
public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final Integer quantity;
    private final Money price;
    private final Money subTotal;

    private OrderItem(Builder builder) {
        super.setId(builder.id);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        orderId = builder.orderId;
    }

    // តើតម្លៃត្រឹមត្រូវដែរឬទេ?
    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }

    public void initializeOrder(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);

    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderItemId id;
        private Product product;
        private Integer quantity;
        private Money price;
        private Money subTotal;
        private OrderId orderId;

        private Builder() {
        }

        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
