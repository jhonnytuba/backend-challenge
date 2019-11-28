package com.invillia.acme.service;

import com.invillia.acme.domain.Store;
import com.invillia.acme.exception.StoreNotFoundException;
import com.invillia.acme.service.repository.StoreRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository repository;

    public StoreService(StoreRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Store findById(Long id) {
        return repository.findById(id).orElseThrow(StoreNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Store save(Store entity) {
        return repository.save(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Store> findAll(Example<Store> example) {
        return repository.findAll(example);
    }
}
