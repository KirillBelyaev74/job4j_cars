package ru.job4j.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "advertisement")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "sold")
    private boolean sold;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    public Advertisement() {
    }

    public Advertisement(String description) {
        this.description = description;
        this.created = new Date();
        this.sold = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id == that.id && sold == that.sold
                && Objects.equals(description, that.description)
                && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, sold);
    }

    @Override
    public String toString() {
        return "Advertisement{"
                + "id = " + id
                + ", description = '" + description + '\''
                + ", created = " + created
                + ", sold = " + sold
                + '}';
    }
}
