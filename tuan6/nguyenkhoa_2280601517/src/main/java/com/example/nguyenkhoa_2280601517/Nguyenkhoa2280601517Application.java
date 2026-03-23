package com.example.nguyenkhoa_2280601517;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

// Phải import 2 thằng Model này vào thì Java mới hiểu nhé bro
import com.example.nguyenkhoa_2280601517.model.Account;
import com.example.nguyenkhoa_2280601517.model.Role;
import com.example.nguyenkhoa_2280601517.repository.AccountRepository;
import com.example.nguyenkhoa_2280601517.repository.RoleRepository;

@SpringBootApplication
public class Nguyenkhoa2280601517Application {

    public static void main(String[] args) {
        SpringApplication.run(Nguyenkhoa2280601517Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(AccountRepository accountRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            // Nếu chưa có quyền nào trong DB thì mới tạo
            if (roleRepo.count() == 0) {
                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                roleRepo.save(adminRole);

                Role userRole = new Role();
                userRole.setName("USER");
                roleRepo.save(userRole);

                // Tạo tài khoản admin mặc định
                Account admin = new Account();
                admin.setLogin_name("admin");
                admin.setPassword(passwordEncoder.encode("123")); // Mật khẩu sẽ tự động được băm nát bằng BCrypt
                admin.getRoles().add(adminRole);
                accountRepo.save(admin);
                
                System.out.println("Tạo xong tài khoản mặc định: admin / 123");
            }
        }; 
    } 
} 