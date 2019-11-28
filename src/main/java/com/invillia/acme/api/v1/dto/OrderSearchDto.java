package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.acme.domain.OrderStatus;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderSearchDto {

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date confirmationDate;

    private OrderStatus status;
    private Long storeId;

    public String getAddress() {
        return address;
    }

    public OrderSearchDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public OrderSearchDto setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderSearchDto setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Long getStoreId() {
        return storeId;
    }

    public OrderSearchDto setStoreId(Long storeId) {
        this.storeId = storeId;
        return this;
    }
}
