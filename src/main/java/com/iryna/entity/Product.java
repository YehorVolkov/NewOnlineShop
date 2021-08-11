package com.iryna.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long id;
    private String name;
    private double price;
    private LocalDateTime creationDate;
}
