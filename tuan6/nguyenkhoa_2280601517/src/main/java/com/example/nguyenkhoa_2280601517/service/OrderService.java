package com.example.nguyenkhoa_2280601517.service;

import com.example.nguyenkhoa_2280601517.model.*;
import com.example.nguyenkhoa_2280601517.repository.OrderRepository;
import com.example.nguyenkhoa_2280601517.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private ProductService productService;
    
    public Order createOrder(Account account, List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            return null;
        }
        
        // Tạo đơn hàng mới
        Order order = new Order();
        order.setAccount(account);
        order.setOrderDate(LocalDateTime.now());
        order.setIsPaid(false);
        
        // Tính tổng tiền
        long totalAmount = 0;
        
        // Thêm từng chi tiết đơn hàng
        for (CartItem cartItem : cartItems) {
            Product product = productService.getProductById(cartItem.getId());
            if (product != null) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setPrice(product.getPrice());
                orderDetail.setQuantity(cartItem.getQuantity());
                orderDetail.setOrder(order);
                
                order.getOrderDetails().add(orderDetail);
                totalAmount += product.getPrice() * cartItem.getQuantity();
            }
        }
        
        order.setTotalAmount(totalAmount);
        
        // Lưu đơn hàng vào DB
        return orderRepository.save(order);
    }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    
    public List<Order> getOrdersByAccount(Long accountId) {
        return orderRepository.findByAccountId(accountId);
    }
    
    public void save(Order order) {
        orderRepository.save(order);
    }
}
