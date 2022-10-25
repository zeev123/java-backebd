package com.hotelsecure.tables;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room", referencedColumnName = "id")
    List<Guests> guests  = new ArrayList<>();

    public Rooms() {
    }

    public Rooms(Long id, Long number, Long capacity) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
