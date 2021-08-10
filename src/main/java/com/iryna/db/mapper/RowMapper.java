package com.iryna.db.mapper;

import com.iryna.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Timestamp timestamp = resultSet.getTimestamp("creation_date");

        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .timestamp(timestamp)
                .build();
    }
}
