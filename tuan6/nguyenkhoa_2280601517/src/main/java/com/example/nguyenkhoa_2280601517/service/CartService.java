package com.example.nguyenkhoa_2280601517.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.List;

import com.example.nguyenkhoa_2280601517.model.CartItem;

@Service
@SessionScope
public class CartService {
    
    private List<CartItem> items = new ArrayList<>();
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public void addToCart(Long productId, String productName, String productImage, long productPrice) {
        // Kiểm tra sản phẩm đã có trong giỏ chưa
        CartItem existingItem = items.stream()
                .filter(item -> item.getId().equals(productId))
                .findFirst()
                .orElse(null);
        
        if (existingItem != null) {
            // Nếu đã có thì tăng số lượng
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            // Nếu chưa có thì tạo mới với số lượng = 1
            CartItem newItem = new CartItem();
            newItem.setId(productId);
            newItem.setName(productName);
            newItem.setImage(productImage);
            newItem.setPrice(productPrice);
            newItem.setQuantity(1);
            items.add(newItem);
        }
    }
    
    public void updateQuantity(Long productId, int quantity) {
        // Cập nhật số lượng
        items.stream()
            .filter(item -> item.getId().equals(productId))
            .findFirst()
            .ifPresent(item -> item.setQuantity(quantity));
    }
    
    public void removeFromCart(Long productId) {
        // Xóa sản phẩm ra khỏi giỏ hàng
        items.removeIf(item -> item.getId().equals(productId));
    }
    
    public void clear() {
        items.clear();
    }
    
    public long getTotalPrice() {
        return items.stream()
                .mapToLong(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
