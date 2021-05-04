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

    @Column(name = "price")
    private int price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "photo")
    private String photo;

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

    public Advertisement(String description, int price) {
        this.description = description;
        this.price = price;
        this.created = new Date();
        this.sold = false;
    }

    public Advertisement(String description, int price, String photo) {
        this.description = description;
        this.price = price;
        this.photo = photo;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        return id == that.id
                && price == that.price
                && sold == that.sold
                && Objects.equals(description, that.description)
                && Objects.equals(created, that.created)
                && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, created, photo, sold);
    }

    @Override
    public String toString() {
        return "Advertisement { " + "id = " + id
                + ", description = '" + description + '\''
                + ", price = " + price
                + ", created = " + created
                + ", photo = '" + photo + '\''
                + ", sold = " + sold + '}';
    }
}
