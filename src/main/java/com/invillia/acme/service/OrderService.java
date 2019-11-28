package com.invillia.acme.service;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderStatus;
import com.invillia.acme.domain.Store;
import com.invillia.acme.exception.OrderCannotBeRefundedException;
import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired(required = false)
    private PaymentService paymentService;

    @Autowired(required = false)
    private StoreService storeService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order save(Order entity) {
        Store store = storeService.findById(entity.getStoreId());

        entity.setStore(store);
        entity.getItems().forEach(orderItem -> orderItem.setOrder(entity));

        return repository.save(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Order> findAll(Example<Order> example) {
        return repository.findAll(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void pay(Order order, Date paymentDate) {
        order.setStatus(OrderStatus.PAID);
        order.setConfirmationDate(paymentDate);

        save(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        save(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order refund(Long id) {
        Order order = findById(id);

        validateIfOrderCanBeRefunded(order);

        paymentService.refund(order);
        order.setStatus(OrderStatus.REFUNDED);

        return order;
    }

    private void validateIfOrderCanBeRefunded(Order order) {
        if (!order.isPaid()) {
            throw new OrderCannotBeRefundedException();
        }

        long diff = new Date().getTime() - order.getConfirmationDate().getTime();
        long diffInDays = diff / (1000 * 60 * 60 * 24);

        if (diffInDays >= 10) {
            throw new OrderCannotBeRefundedException();
        }
    }
}
