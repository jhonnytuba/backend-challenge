package com.invillia.acme.api.v1.controller;

import com.invillia.acme.api.v1.dto.PaymentEntryDto;
import com.invillia.acme.api.v1.dto.PaymentResponseDto;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.mapper.ApiMapper;
import com.invillia.acme.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final ApiMapper apiMapper;
    private final PaymentService service;

    public PaymentController(ApiMapper apiMapper, PaymentService service) {
        this.apiMapper = apiMapper;
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity persist(@Valid @RequestBody PaymentEntryDto entryDto) {
        Payment entity = service.save(toEntity(entryDto));
        PaymentResponseDto responseDto = toResponseDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    private PaymentResponseDto toResponseDto(Payment entity) {
        return apiMapper.map(entity, PaymentResponseDto.class);
    }

    private Payment toEntity(PaymentEntryDto entryDto) {
        return apiMapper.map(entryDto, Payment.class);
    }
}
