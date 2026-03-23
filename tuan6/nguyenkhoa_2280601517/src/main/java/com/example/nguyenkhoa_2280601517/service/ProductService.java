package com.example.nguyenkhoa_2280601517.service;

import com.example.nguyenkhoa_2280601517.model.Product;
import com.example.nguyenkhoa_2280601517.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public void saveProduct(Product product) { productRepository.save(product); }
    public Product getProductById(Long id) { return productRepository.findById(id).orElse(null); }
    public void deleteProduct(Long id) { productRepository.deleteById(id); }
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}