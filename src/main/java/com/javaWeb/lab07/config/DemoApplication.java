package com.javaWeb.lab07.config;

import com.javaWeb.lab07.model.User;
import com.javaWeb.lab07.model.Dashboard;
import com.javaWeb.lab07.repository.UserRepository;
import com.javaWeb.lab07.repository.DashboardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           DashboardRepository dashboardRepository,
                           PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear usuario de prueba
            if (userRepository.findByUsername("testuser").isEmpty()) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("1234"));
                user.setRole("ROLE_USER");
                userRepository.save(user);
            }

            // Crear dashboards de prueba
            if (dashboardRepository.count() == 0) {
                Dashboard dash1 = new Dashboard();
                dash1.setTitle("Ventas Q1");
                dash1.setDescription("Resumen de ventas del primer trimestre");
                dash1.setOwner("testuser");

                Dashboard dash2 = new Dashboard();
                dash2.setTitle("Usuarios Activos");
                dash2.setDescription("Monitoreo de usuarios en la plataforma");
                dash2.setOwner("testuser");

                dashboardRepository.save(dash1);
                dashboardRepository.save(dash2);
            }
        };
    }
}
