package com.hotelsecure.tables;


import javax.persistence.*;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.XMLFormatter;

@Entity
@Table(name = "Messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "start_date", nullable = false)
    private Date start_date;

    @Column(name = "data", nullable = true)
    private String data;

    @Column(name = "desccription", nullable = true)
    private String desccription;

    @Column(name = "praiority", nullable = false)
    private Long praiority;

    @Column(name = "mission_id", nullable = true)
    private Long mission_id;

    @Column(name = "user_id", nullable = true)
    private Long user_id;


    public Messages() {
    }

    public Messages(String status, String type, Date start_date, String data, String desccription, Long praiority, Long mission_id) {
        this.status = status;
        this.type = type;
        this.start_date = start_date;
        this.data = data;
        this.desccription = desccription;
        this.praiority = praiority;
        this.mission_id = mission_id;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    public Long getPraiority() {
        return praiority;
    }

    public void setPraiority(Long praiority) {
        this.praiority = praiority;
    }

    public Long getMission_id() {
        return mission_id;
    }

    public void setMission_id(Long mission_id) {
        this.mission_id = mission_id;
    }
}
