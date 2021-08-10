package com.iryna.entity;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long id;
    private String name;
    private double price;
    private Timestamp timestamp;
}
