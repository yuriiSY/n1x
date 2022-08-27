package com.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @GeneratedValue
            (generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private double sum;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Product> products;
    private LocalDateTime time;

    public Invoice() {
     //   this.id = UUID.randomUUID().toString();
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", products=" + products +
                ", time=" + time +
                '}';

    }
}
