package com.example.luat_tuc_hon_uoc_trong_lang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Cấu hình phân quyền Spring Security:
     * - Giai đoạn 1 (Chạy offline / Thử nghiệm số hóa): Cho phép truy cập form nhập liệu, danh sách và tài nguyên tĩnh.
     * - Cấu hình sẵn CSRF và cơ chế form login để sẵn sàng kích hoạt bảo vệ khi đẩy lên môi trường Online ở giai đoạn 2.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Cho phép tài nguyên tĩnh (css, js, images, webjars...)
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
                // Cho phép truy cập các chức năng số hóa (Form nhập liệu, Lưu, Danh sách, Xem chi tiết)
                .requestMatchers("/", "/customs/**").permitAll()
                // Các request khác yêu cầu xác thực
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/customs")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Cấu hình tài khoản mẫu (phục vụ quản trị khi chuyển sang chế độ Online)
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN", "ARCHIVIST")
                .build();

        UserDetails user = User.builder()
                .username("canbo_sohoa")
                .password(passwordEncoder.encode("sohoa123"))
                .roles("ARCHIVIST")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
