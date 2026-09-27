package dtwg.mptc.ecommerce.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount) {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    //verified < 0
    public boolean isGreaterThanZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    //verified money input
    public boolean isGreaterThan(Money money) {
        return amount.compareTo(money.amount()) > 0;
    }

    //extra money
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.amount)));
    }

    //sub
    public Money subtract(Money money){
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

    //mul
    public Money multiply(int multiplier){
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }


    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return amount.stripTrailingZeros().hashCode();
    }
}
