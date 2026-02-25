package com.example.jdata_01.service;

import com.example.jdata_01.model.Product;
import com.example.jdata_01.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository = null;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        product.setId(id); // Убедимся, что ID совпадает
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProductsByCategory(String category) {
        return productRepository.findByCategoriesContaining(category);
    }

    public List<Product> findAvailableProducts() {
        return productRepository.findByQuantityGreaterThan(0);
    }
}
