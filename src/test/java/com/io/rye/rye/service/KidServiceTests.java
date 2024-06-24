package com.io.rye.rye.service;

import com.io.rye.rye.dto.KidRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.repository.KidRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class KidServiceTests {

    @Mock
    private KidRepository kidRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private KidService kidService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testKidLogin_ValidCredentials() {

        Kid kid = new Kid();
        kid.setId(1);
        kid.setUsername("kid");
        kid.setPassword("hashedPassword");

        when(kidRepository.findByUsername("kid")).thenReturn(Optional.of(kid));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("kid");
        loginForm.setPassword("password");
        String token = kidService.kidLogin(loginForm);

        assertNotNull(token);
    }

    @Test
    void testKidLogin_InvalidLogin() {

        Kid kid = new Kid();
        kid.setId(1);
        kid.setUsername("kid");
        kid.setPassword("hashedPassword");

        when(kidRepository.findByUsername("kid")).thenReturn(Optional.empty());

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("kid");
        loginForm.setPassword("password");
        String token = kidService.kidLogin(loginForm);

        assertNull(token);
    }

    @Test
    void testKidLogin_InvalidPassword() {

        Kid kid = new Kid();
        kid.setId(1);
        kid.setUsername("kid");
        kid.setPassword("hashedPassword");

        when(kidRepository.findByUsername("kid")).thenReturn(Optional.of(kid));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(false);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("kid");
        loginForm.setPassword("password");
        String token = kidService.kidLogin(loginForm);

        assertNull(token);
    }

    @Test
    void testKidRegister_ValidParameters() {
        // Arrange
        KidRegisterForm form = new KidRegisterForm();
        form.setUsername("testUser");
        form.setPassword("testPass");

        Kid kid = new Kid();
        kid.setUsername("testUser");
        kid.setPassword("encodedPass");

        when(passwordEncoder.encode("testPass")).thenReturn("encodedPass");
        when(kidRepository.save(Mockito.any(Kid.class))).thenReturn(kid);

        // Act & Assert
        Kid result = kidService.registerKid(form);

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("encodedPass", result.getPassword());
    }

    @Test
    void testKidRegister_InvalidInput() {
        // Arrange
        KidRegisterForm form = new KidRegisterForm();
        form.setUsername(null);
        form.setPassword("testPass");

        Kid kid = new Kid();
        kid.setUsername("testUser");
        kid.setPassword("encodedPass");

        when(passwordEncoder.encode("testPass")).thenReturn("encodedPass");
        when(kidRepository.save(Mockito.any(Kid.class))).thenReturn(kid);

        // Act & Assert
        Kid result = kidService.registerKid(form);

        // Assert
        assertNull(result);
    }
}