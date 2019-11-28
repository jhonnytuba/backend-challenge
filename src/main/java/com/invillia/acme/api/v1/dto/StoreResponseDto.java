package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreResponseDto extends StoreEntryDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public StoreResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
}
