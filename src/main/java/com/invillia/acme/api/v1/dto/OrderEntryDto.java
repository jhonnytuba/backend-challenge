package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntryDto {

    @NotBlank
    private String address;

    @NotNull
    private Long storeId;

    @NotEmpty
    @Size(min = 1)
    private List<OrderItemEntryDto> items;

    public String getAddress() {
        return address;
    }

    public OrderEntryDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getStoreId() {
        return storeId;
    }

    public OrderEntryDto setStoreId(Long storeId) {
        this.storeId = storeId;
        return this;
    }

    public List<OrderItemEntryDto> getItems() {
        return items;
    }

    public OrderEntryDto setItems(List<OrderItemEntryDto> items) {
        this.items = items;
        return this;
    }
}
