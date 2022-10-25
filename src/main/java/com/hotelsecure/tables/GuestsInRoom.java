package com.hotelsecure.tables;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GuestsInRoom {
    @Column(name = "room_local_id", nullable = false)
    private Long room_local_id;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "room", nullable = false)
    private String room;

    public GuestsInRoom() {
    }

    public GuestsInRoom(Long room_local_id, Long capacity, Long number, Long id, String name, String room) {
        this.room_local_id = room_local_id;
        this.capacity = capacity;
        this.number = number;
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public Long getRoom_local_id() {
        return room_local_id;
    }

    public void setRoom_local_id(Long room_local_id) {
        this.room_local_id = room_local_id;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
