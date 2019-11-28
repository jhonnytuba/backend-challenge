package com.invillia.acme.service;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import com.invillia.acme.exception.OrderCannotBeUpdateException;
import com.invillia.acme.service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired(required = false)
    private OrderService orderService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Payment save(Payment entity) {
        Order order = orderService.findById(entity.getOrderId());

        if (order.isCancelledOrRefunded() || isOrderAlreadyPaid(order, entity)) {
            throw new OrderCannotBeUpdateException();
        }

        processPaymentInOrder(order, entity);

        return repository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void refund(Order order) {
        save(new Payment()
                .setOrder(order)
                .setPaymentDate(new Date())
                .setStatus(PaymentStatus.REFUNDED)
                .setCreditCard(""));
    }

    private void processPaymentInOrder(Order order, Payment saved) {
        if (saved.getStatus() == PaymentStatus.CONCLUDED) {
            orderService.pay(order, saved.getPaymentDate());
        } else if (saved.getStatus() == PaymentStatus.CANCELLED) {
            orderService.cancel(order);
        }
    }

    private boolean isOrderAlreadyPaid(Order order, Payment entity) {
        return order.isPaid() && entity.isConcluded();
    }
}
