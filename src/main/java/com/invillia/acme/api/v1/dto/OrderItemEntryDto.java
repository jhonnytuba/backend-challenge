package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemEntryDto {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Integer quantity;

    public String getDescription() {
        return description;
    }

    public OrderItemEntryDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public OrderItemEntryDto setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemEntryDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
