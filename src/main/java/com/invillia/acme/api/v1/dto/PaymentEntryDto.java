package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.acme.domain.PaymentStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentEntryDto {

    @NotNull
    private PaymentStatus status;

    @NotNull
    private Date paymentDate;

    @NotBlank
    private String creditCard;

    @NotNull
    private Long orderId;

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentEntryDto setStatus(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public PaymentEntryDto setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public PaymentEntryDto setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public PaymentEntryDto setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
}
