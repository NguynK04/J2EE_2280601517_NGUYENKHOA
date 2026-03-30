package com.example.nguyenkhoa_2280601517.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    
    private Long id;
    private String name;
    private String image;
    private long price;
    private int quantity;
}
