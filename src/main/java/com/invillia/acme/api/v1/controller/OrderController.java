package com.invillia.acme.api.v1.controller;

import com.invillia.acme.api.v1.dto.OrderEntryDto;
import com.invillia.acme.api.v1.dto.OrderResponseDto;
import com.invillia.acme.api.v1.dto.OrderSearchDto;
import com.invillia.acme.domain.Order;
import com.invillia.acme.mapper.ApiMapper;
import com.invillia.acme.service.OrderService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private static final ExampleMatcher exampleMatcher = ExampleMatcher
            .matchingAny()
            .withMatcher("address", ignoreCase().contains())
            .withMatcher("confirmationDate", exact())
            .withMatcher("status", exact())
            .withMatcher("storeId", exact());

    private final ApiMapper apiMapper;
    private final OrderService service;

    public OrderController(ApiMapper apiMapper, OrderService service) {
        this.apiMapper = apiMapper;
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity persist(@Valid @RequestBody OrderEntryDto entryDto) {
        Order entity = service.save(toEntity(entryDto));
        OrderResponseDto responseDto = toResponseDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<OrderResponseDto> findAll(OrderSearchDto entryDto) {
        Order entity = toEntity(entryDto);
        List<Order> results = service.findAll(getExample(entity));

        return results.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/refund/{id}",
            produces = APPLICATION_JSON_UTF8_VALUE)
    public OrderResponseDto refund(@PathVariable Long id) {
        return toResponseDto(service.refund(id));
    }

    private Example<Order> getExample(Order entity) {
        return Example.of(entity, exampleMatcher);
    }

    private OrderResponseDto toResponseDto(Order entity) {
        return apiMapper.map(entity, OrderResponseDto.class);
    }

    private Order toEntity(Object entryDto) {
        return apiMapper.map(entryDto, Order.class);
    }
}
