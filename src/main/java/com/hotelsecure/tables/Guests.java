package com.hotelsecure.tables;

import javax.persistence.*;

@Entity
@Table(name = "Guests")
public class Guests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "room", nullable = false)
    private Long room;

    public Guests() {
    }

    public Guests(Long id, String name, Long room) {
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }
}
