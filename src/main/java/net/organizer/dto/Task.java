package net.organizer.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Nikolay on 15.12.2015.
 */
public class Task {
    private Integer id;
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date targetDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", targetDate=" + targetDate +
                '}';
    }
}
