package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.acme.domain.OrderStatus;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDto extends OrderEntryDto {

    private Long id;
    private Date confirmationDate;
    private OrderStatus status;
    private List<PaymentResponseDto> payments;

    public Long getId() {
        return id;
    }

    public OrderResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public OrderResponseDto setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderResponseDto setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public List<PaymentResponseDto> getPayments() {
        return payments;
    }

    public OrderResponseDto setPayments(List<PaymentResponseDto> payments) {
        this.payments = payments;
        return this;
    }
}
