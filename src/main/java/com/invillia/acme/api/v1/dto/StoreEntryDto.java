package com.invillia.acme.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreEntryDto {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    public String getName() {
        return name;
    }

    public StoreEntryDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StoreEntryDto setAddress(String address) {
        this.address = address;
        return this;
    }
}
