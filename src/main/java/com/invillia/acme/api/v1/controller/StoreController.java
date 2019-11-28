package com.invillia.acme.api.v1.controller;

import com.invillia.acme.api.v1.dto.StoreEntryDto;
import com.invillia.acme.api.v1.dto.StoreResponseDto;
import com.invillia.acme.domain.Store;
import com.invillia.acme.mapper.ApiMapper;
import com.invillia.acme.service.StoreService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private static final ExampleMatcher exampleMatcher = ExampleMatcher
            .matchingAny()
            .withMatcher("name", ignoreCase().contains())
            .withMatcher("address", ignoreCase().contains());

    private final ApiMapper apiMapper;
    private final StoreService service;

    public StoreController(ApiMapper apiMapper, StoreService service) {
        this.apiMapper = apiMapper;
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity persist(@Valid @RequestBody StoreEntryDto entryDto) {
        Store entity = service.save(toEntity(entryDto));
        StoreResponseDto responseDto = toResponseDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping(path = "{id}",
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE)
    public StoreResponseDto update(@PathVariable Long id, @Valid @RequestBody StoreEntryDto entryDto) {
        Store entity = service.findById(id);
        apiMapper.map(entryDto, entity);

        return toResponseDto(service.save(entity));
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<StoreResponseDto> findAll(StoreEntryDto entryDto) {
        Store entity = toEntity(entryDto);
        List<Store> results = service.findAll(getExample(entity));

        return results.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private Example<Store> getExample(Store entity) {
        return Example.of(entity, exampleMatcher);
    }

    private StoreResponseDto toResponseDto(Store entity) {
        return apiMapper.map(entity, StoreResponseDto.class);
    }

    private Store toEntity(StoreEntryDto entryDto) {
        return apiMapper.map(entryDto, Store.class);
    }
}
