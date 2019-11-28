package com.invillia.acme.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "bc_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "confirmation_date")
    private Date confirmationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.CREATED;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Payment> payments;

    public boolean isCancelledOrRefunded() {
        return status == OrderStatus.CANCELLED || status == OrderStatus.REFUNDED;
    }

    public boolean isPaid() {
        return status == OrderStatus.PAID;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public Order setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Store getStore() {
        return store;
    }

    public Order setStore(Store store) {
        this.store = store;
        return this;
    }

    public Long getStoreId() {
        return Optional.ofNullable(store).map(Store::getId).orElse(null);
    }

    public Order setStoreId(Long storeId) {
        this.store = new Store().setId(storeId);
        return this;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order setItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public Order setPayments(List<Payment> payments) {
        this.payments = payments;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
