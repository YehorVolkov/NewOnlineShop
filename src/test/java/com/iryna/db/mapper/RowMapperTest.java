package com.iryna.db.mapper;

import com.iryna.entity.Product;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class RowMapperTest {

    @Test
    void mapRow() throws SQLException {
        RowMapper rowMapper = new RowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        LocalDateTime localDateTime = LocalDateTime.now();

        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("pen");
        when(resultSet.getDouble("price")).thenReturn(12.99);
        when(resultSet.getTimestamp("creation_date")).thenReturn(Timestamp.valueOf(localDateTime));

        Product product = rowMapper.mapRow(resultSet);

        assertNotNull(product);

        assertEquals(1, product.getId());
        assertEquals("pen", product.getName());
        assertEquals(12.99, product.getPrice());
        assertEquals(Timestamp.valueOf(localDateTime), product.getCreationDate());
    }
}