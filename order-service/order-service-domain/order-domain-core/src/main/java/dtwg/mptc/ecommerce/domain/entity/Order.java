package dtwg.mptc.ecommerce.domain.entity;

import dtwg.mptc.ecommerce.domain.exception.OrderDomainException;
import dtwg.mptc.ecommerce.domain.valueobject.*;

import java.util.List;
import java.util.UUID;


public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;
    private final BusinessId businessId;
    private final StreetAddress streetAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    //====================== Start Critical Business Login ==========================

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        businessId = builder.businessId;
        streetAddress = builder.streetAddress;
        price = builder.money;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.status;
        failureMessages = builder.failureMessage;
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || super.getId() != null) {
            throw new OrderDomainException("Order is not in correct status for initialization");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().amount() +
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
            throw new OrderDomainException("Total price: " + price.amount()
                    + " is not equal to order items total price: " + orderItemsTotalPrice.amount());
        }
    }

    private void initializeOrderItems() {
        int itemCount = 1;
        for (OrderItem item : items) {
            item.initializeOrder(super.getId(), new OrderItemId(itemCount++));
        }
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for approve operation");
        }
        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for init cancel operation");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(this.failureMessages);

    }

    public void cancel(List<String> failureMessages) {
        if (!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
            throw new OrderDomainException("Order is not in correct state for cancel operation");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);

    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (failureMessages != null && this.failureMessages != null) {
            this.failureMessages.addAll(
                    failureMessages.stream().filter(message -> !message.isBlank()).toList()
            );
        }

        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    //====================== End Critical Business Login ==========================


    public CustomerId getCustomerId() {
        return customerId;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public StreetAddress getStreetAddress() {
        return streetAddress;
    }

    public Money getMoney() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private BusinessId businessId;
        private StreetAddress streetAddress;
        private Money money;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus status;
        private List<String> failureMessage;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
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

        public Builder money(Money val) {
            money = val;
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

        public Builder status(OrderStatus val) {
            status = val;
            return this;
        }

        public Builder failureMessage(List<String> val) {
            failureMessage = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
