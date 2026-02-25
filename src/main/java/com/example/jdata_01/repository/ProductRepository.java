package com.example.jdata_01.repository;

import com.example.jdata_01.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // Spring Data MongoDB автоматически реализует эти методы
    List<Product> findByName(String name);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategoriesContaining(String category);
    List<Product> findByQuantityGreaterThan(Integer quantity);
}
