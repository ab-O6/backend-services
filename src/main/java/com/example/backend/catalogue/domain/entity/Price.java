package com.example.backend.catalogue.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
public class Price {

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    @Setter
    @Column(length = 3)
    private String currency;
    
    protected Price() {}

    public Price(BigDecimal amount, String currency) {
        setAmount(amount);
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount != null ? amount.setScale(2, RoundingMode.HALF_UP) : null;
    }
}
