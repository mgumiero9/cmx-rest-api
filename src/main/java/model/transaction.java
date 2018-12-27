package model;

import java.math.BigDecimal;

public class transaction {

    private Long timestamp;
    private BigDecimal amount;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "transaction{" +
                "timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
