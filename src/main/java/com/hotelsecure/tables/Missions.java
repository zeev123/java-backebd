package com.hotelsecure.tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Missions")
public class Missions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "the_missions", nullable = false)
    private String the_missions;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "start_date", nullable = false)
    private Date start_date;

    @Column(name = "start_work_date", nullable = true)
    private Date start_work_date;

    @Column(name = "end_date", nullable = true)
    private Date end_date;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "missions_message", nullable = false)
    private String missions_message;

    @Column(name = "sys", nullable = true)
    private String sys;

    @Column(name = "progrem_language", nullable = true)
    private String progrem_language;

    @Column(name = "note", nullable = true)
    private String note;

    @Column(name = "photo_path", nullable = true)
    private String photo_path;

    @Column(name = "should_end", nullable = true)
    private String should_end;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mission_id", referencedColumnName = "id")
    List<Messages> messages  = new ArrayList<>();

    public Missions() {
    }

    public Missions(String the_missions, Long user_id, Date start_date, Date start_work_date, Date end_date, String status, String type, String missions_message, String sys, String progrem_language, String note, String photo_path, String should_end) {
        this.the_missions = the_missions;
        this.user_id = user_id;
        this.start_date = start_date;
        this.start_work_date = start_work_date;
        this.end_date = end_date;
        this.status = status;
        this.type = type;
        this.missions_message = missions_message;
        this.sys = sys;
        this.progrem_language = progrem_language;
        this.note = note;
        this.photo_path = photo_path;
        this.should_end = should_end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThe_missions() {
        return the_missions;
    }

    public void setThe_missions(String the_missions) {
        this.the_missions = the_missions;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getStart_work_date() {
        return start_work_date;
    }

    public void setStart_work_date(Date start_work_date) {
        this.start_work_date = start_work_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

    public String getMissions_message() {
        return missions_message;
    }

    public void setMissions_message(String missions_message) {
        this.missions_message = missions_message;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getProgrem_language() {
        return progrem_language;
    }

    public void setProgrem_language(String progrem_language) {
        this.progrem_language = progrem_language;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getShould_end() {
        return should_end;
    }

    public void setShould_end(String should_end) {
        this.should_end = should_end;
    }
}
