package com.galvanize.gmdb.gmdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name="Reviewer")
public class Reviewer {
    @Id
    private Integer id;
    private String username;
    private String joinedDate;
    private Integer numberOfReviews;
    public Reviewer(String username){
        this.username=username;
    }
}