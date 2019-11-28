package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponseDto extends PaymentEntryDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public PaymentResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
}
