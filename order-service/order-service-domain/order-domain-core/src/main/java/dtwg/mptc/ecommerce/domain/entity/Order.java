package dtwg.mptc.ecommerce.domain.entity;

import dtwg.mptc.ecommerce.domain.exception.OrderDomainException;
import dtwg.mptc.ecommerce.domain.valueobject.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class Order extends AggregateRoot<OrderId>{
    private final CustomerId customerId;
    private final BusinessId businessId;
    private final StreetAddress streetAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        businessId = builder.businessId;
        streetAddress = builder.streetAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    //--------business logic
    public void validateOrder(){
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not valid for product: " + orderItem.getProduct().getId().value());
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotalPrice = items.stream()
                .map(orderItem -> {
                    validateItemPrice(orderItem);
                    return orderItem.getSubTotal();
                })
                .reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotalPrice)) {
            throw new OrderDomainException("Total price: " + price.getAmount()
                    + " is not equal to order items total price: " + orderItemsTotalPrice.getAmount());
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || super.getId() != null) {
            throw new OrderDomainException("Order is not in correct status for initialization");
        }
    }

    public void initializeOrder(){
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        items.forEach(item -> item.setId(new OrderItemId(UUID.randomUUID())));
    }
    public void pay(){
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct status for payment");
        }
        orderStatus = OrderStatus.PAID;

    }
    public void approve(){
        if (orderStatus != orderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct status for approval");
        }
        orderStatus = OrderStatus.APPROVED;

    }
    public void initCancel(List<String> failureMessages){
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct status for cancel");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessage(failureMessages);

    }

    public void cancel(List<String> failureMessages){
        if (orderStatus != OrderStatus.CANCELLING ) {
            throw new OrderDomainException("Order is not in correct status for cancel");
        }

    }

    private void updateFailureMessage(List<String> failureMessages) {
        if (failureMessages != null && this.failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message ->!message.isBlank()).toList());
        }
        orderStatus = OrderStatus.CANCELLED;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private BusinessId businessId;
        private StreetAddress streetAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }



        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder streetAddress(StreetAddress val) {
            streetAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}