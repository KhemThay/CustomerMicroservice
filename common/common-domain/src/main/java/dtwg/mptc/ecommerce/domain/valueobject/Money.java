package dtwg.mptc.ecommerce.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(
        BigDecimal amount
) {
    public static final Money ZERO = new Money(BigDecimal.ZERO) ;

    //verify money > 0
    public boolean isGreaterThanZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    //verify money input
    public boolean inGreaterThan(Money money) {
        return amount.compareTo(money.amount) > 0;
    }

    //add money
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.amount)));
    }

    public Money subtract(Money money){
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

    //
    public Money multiply(int multiplier){
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

    //Round Money
    private BigDecimal setScale(BigDecimal inputAmount){
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(setScale(amount),setScale(money.amount));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(setScale(amount));
    }

}