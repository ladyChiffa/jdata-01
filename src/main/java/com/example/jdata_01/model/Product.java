package com.example.jdata_01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "products") // Указываем название коллекции в MongoDB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private List<String> categories;
    private Address manufacturer;
    private List<Review> reviews;

    public void setId(String id) {
        this.id = id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Review {
        private String userId;
        private String userName;
        private Integer rating;
        private String comment;
        private String date;
    }
}
