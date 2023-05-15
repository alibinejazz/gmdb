package com.galvanize.gmdb.gmdb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer R_id;
    private Integer reviewerId;
    private Integer movieId;
    private String R_text;
    private String Date_Time;
    public Review(String R_text,String Date_Time){
        this.R_text=R_text;
        this.Date_Time=Date_Time;
    }
}

