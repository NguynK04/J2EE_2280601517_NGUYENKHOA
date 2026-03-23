package com.example.nguyenkhoa_2280601517.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên thiết bị không được để trống")
    private String name;

    private String image;

    @Min(value = 0, message = "Giá không được nhỏ hơn 0")
    private long price;

    // BỔ SUNG: Cột lưu nội dung chi tiết (Dùng TEXT để lưu được bài viết dài)
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}