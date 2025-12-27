package com.project.flowly.shared.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Value Object inmutable para representar cantidades monetarias con soporte multi-moneda.
 * Internamente almacena el monto en la unidad más pequeña de la moneda (ej: centavos para USD/COP).
 *
 * Ejemplos:
 * - COP 1.500,00 → amount = 150000 (centavos)
 * - USD 10.99   → amount = 1099 (cents)
 */
public final class Money {
    private final long amount;
    private final Currency currency;

    private Money(long amount, Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Crea un objeto Money a partir de una cantidad en la unidad más pequeña (ej: centavos).
     */
    public static Money ofMinor(long minorAmount, Currency currency) {
        return new Money(minorAmount, currency);
    }

    /**
     * Crea un objeto Money a partir de un valor decimal (ej: "1500.00").
     * Útil para parsear entradas de usuario o APIs.
     */
    public static Money of(String decimalValue, Currency currency) {
        if (decimalValue == null || decimalValue.trim().isEmpty()) {
            throw new IllegalArgumentException("Decimal value cannot be null or empty");
        }
        BigDecimal value = new BigDecimal(decimalValue.trim());
        int fractionDigits = currency.getDefaultFractionDigits();
        BigDecimal multiplier = BigDecimal.valueOf(10).pow(fractionDigits);
        long minorAmount = value.multiply(multiplier).longValueExact();
        return ofMinor(minorAmount, currency);
    }

    /**
     * Crea un objeto Money para COP (pesos colombianos).
     * Ej: Money.cop("1500.00") → 150000 centavos
     */
    public static Money cop(String decimalValue) {
        return of(decimalValue, Currency.getInstance("COP"));
    }

    /**
     * Crea un objeto Money para USD.
     */
    public static Money usd(String decimalValue) {
        return of(decimalValue, Currency.getInstance("USD"));
    }

    /**
     * Crea un objeto Money para EUR.
     */
    public static Money eur(String decimalValue) {
        return of(decimalValue, Currency.getInstance("EUR"));
    }

    public long amount() {
        return amount;
    }

    public Currency currency() {
        return currency;
    }

    /**
     * Devuelve el monto en formato decimal (ej: "1500.00")
     */
    public String toDecimalString() {
        int fractionDigits = currency.getDefaultFractionDigits();
        BigDecimal value = BigDecimal.valueOf(amount)
                .divide(BigDecimal.valueOf((long) Math.pow(10, fractionDigits)));
        return value.setScale(fractionDigits, BigDecimal.ROUND_HALF_EVEN).toString();
    }

    public Money add(Money other) {
        validateSameCurrency(other);
        return ofMinor(this.amount + other.amount, this.currency);
    }

    public Money subtract(Money other) {
        validateSameCurrency(other);
        if (this.amount < other.amount) {
            throw new IllegalArgumentException("Result would be negative");
        }
        return ofMinor(this.amount - other.amount, this.currency);
    }

    private void validateSameCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    String.format("Cannot operate on different currencies: %s vs %s",
                            this.currency.getCurrencyCode(), other.currency.getCurrencyCode())
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return currency.getSymbol() + " " + toDecimalString();
    }
}