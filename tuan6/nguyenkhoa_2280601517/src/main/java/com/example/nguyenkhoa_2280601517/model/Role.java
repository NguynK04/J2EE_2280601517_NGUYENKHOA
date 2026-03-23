package com.example.nguyenkhoa_2280601517.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}