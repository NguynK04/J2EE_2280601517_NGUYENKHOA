package com.example.nguyenkhoa_2280601517.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.nguyenkhoa_2280601517.model.Product;
import com.example.nguyenkhoa_2280601517.model.Account;
import com.example.nguyenkhoa_2280601517.service.CartService;
import com.example.nguyenkhoa_2280601517.service.ProductService;
import com.example.nguyenkhoa_2280601517.service.AccountService;
import com.example.nguyenkhoa_2280601517.service.OrderService;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    private final CartService cartService;
    private final ProductService productService;
    private final AccountService accountService;
    private final OrderService orderService;
    
    public CartController(CartService cartService, ProductService productService, 
                         AccountService accountService, OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.accountService = accountService;
        this.orderService = orderService;
    }
    
    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotalPrice());
        return "cart/list";
    }
    
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cartService.addToCart(product.getId(), product.getName(), product.getImage(), product.getPrice());
        }
        return "redirect:/cart";
    }
    
    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }
    
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }
    
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
    
    @PostMapping("/checkout")
    public String checkout() {
        // Lấy thông tin người dùng hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Kiểm tra nếu chưa đăng nhập
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            return "redirect:/login";
        }
        
        String username = auth.getName();
        
        // Lấy Account từ username
        Account account = accountService.getAccountByUsername(username);
        if (account == null) {
            return "redirect:/login";
        }
        
        // Tạo đơn hàng từ giỏ hàng
        orderService.createOrder(account, cartService.getItems());
        
        // Xóa giỏ hàng
        cartService.clear();
        
        // Chuyển hướng đến trang thành công
        return "redirect:/cart/success";
    }
    
    @GetMapping("/success")
    public String orderSuccess() {
        return "cart/success";
    }
}
