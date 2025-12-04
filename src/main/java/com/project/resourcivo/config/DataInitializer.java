package com.project.resourcivo.config;

import com.project.resourcivo.model.Role;
import com.project.resourcivo.model.User;
import com.project.resourcivo.repository.RoleRepository;
import com.project.resourcivo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if they don't exist
        if (roleRepository.count() == 0) {
            Role adminRole = new Role(Role.RoleName.ROLE_ADMIN);
            Role facultyRole = new Role(Role.RoleName.ROLE_FACULTY);
            Role studentRole = new Role(Role.RoleName.ROLE_STUDENT);

            roleRepository.save(adminRole);
            roleRepository.save(facultyRole);
            roleRepository.save(studentRole);

            System.out.println("✅ Default roles created: ADMIN, FACULTY, STUDENT");
        } else {
            System.out.println("✅ Roles already exist in database");
        }

        // Initialize default user if it doesn't exist
        if (!userRepository.existsByUsername("username")) {
            User user = new User();
            user.setUsername("username");
            user.setEmail("username@example.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);

            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            user.setRoles(roles);

            userRepository.save(user);
            System.out.println("✅ Default user created: username/password");
        } else {
            System.out.println("✅ Default user already exists");
        }
    }
}
