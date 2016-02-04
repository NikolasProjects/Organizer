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
    private Integer authorId;
    private boolean outDated;

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public boolean isOutDated() {
        return outDated;
    }

    public void setOutDated(boolean outDated) {
        this.outDated = outDated;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", targetDate=" + targetDate +
                ", authorId=" + authorId +
                ", outDated=" + outDated +
                '}';
    }
}
