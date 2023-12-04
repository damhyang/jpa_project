package com.example.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Picture {
    @Id
    private int seq;
    private String memberId;
    private String fileName;
    private String updateDate;
}
