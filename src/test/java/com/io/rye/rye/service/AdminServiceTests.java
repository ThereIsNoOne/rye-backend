package com.io.rye.rye.service;

import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Admin;
import com.io.rye.rye.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class AdminServiceTests {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdminLogin_ValidCredentials() {

        Admin admin = new Admin();
        admin.setId(1);
        admin.setLogin("admin");
        admin.setPassword("hashedPassword");

        when(adminRepository.findByLogin("admin")).thenReturn(Optional.of(admin));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("admin");
        loginForm.setPassword("password");
        String token = adminService.adminLogin(loginForm);

        assertNotNull(token);
    }

    @Test
    void testAdminLogin_InvalidLogin() {

        Admin admin = new Admin();
        admin.setId(1);
        admin.setLogin("admin");
        admin.setPassword("hashedPassword");

        when(adminRepository.findByLogin("admin")).thenReturn(Optional.empty());

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("admin");
        loginForm.setPassword("password");
        String token = adminService.adminLogin(loginForm);

        assertNull(token);
    }

    @Test
    void testAdminLogin_InvalidPassword() {

        Admin admin = new Admin();
        admin.setId(1);
        admin.setLogin("admin");
        admin.setPassword("hashedPassword");

        when(adminRepository.findByLogin("admin")).thenReturn(Optional.of(admin));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(false);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("admin");
        loginForm.setPassword("password");
        String token = adminService.adminLogin(loginForm);

        assertNull(token);
    }
}