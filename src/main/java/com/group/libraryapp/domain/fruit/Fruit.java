package com.group.libraryapp.domain.fruit;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fruits")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25)
    private String name;

    private LocalDate warehousingDate;

    private Long price;

    @Column(length = 10 ,columnDefinition = "CHECK (status IN ('SOLD', 'NOT_SOLD'))")
    private String status;

    public Fruit(String name,
                 LocalDate warehousingDate,
                 Long price,
                 String status) {

    }

    public Fruit() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public Long getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String sold) {
    }
}
