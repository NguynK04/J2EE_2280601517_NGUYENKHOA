package com.example.nguyenkhoa_2280601517.repository;

import com.example.nguyenkhoa_2280601517.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Phải có dòng này thì AccountController mới gọi được nhé bro
    Role findByName(String name);
}