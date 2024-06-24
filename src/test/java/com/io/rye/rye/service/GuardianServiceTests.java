package com.io.rye.rye.service;

import com.io.rye.rye.dto.GuardianRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.repository.GuardianRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GuardianServiceTests {

    @Mock
    private GuardianRepository guardianRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private GuardianService guardianService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardianLogin_ValidCredentials() {

        Guardian guardian = new Guardian();
        guardian.setId(1);
        guardian.setUsername("guardian");
        guardian.setPassword("hashedPassword");

        when(guardianRepository.findByUsername("guardian")).thenReturn(Optional.of(guardian));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("guardian");
        loginForm.setPassword("password");
        String token = guardianService.guardianLogin(loginForm);

        assertNotNull(token);
    }

    @Test
    void testGuardianLogin_InvalidLogin() {

        Guardian guardian = new Guardian();
        guardian.setId(1);
        guardian.setUsername("guardian");
        guardian.setPassword("hashedPassword");

        when(guardianRepository.findByUsername("guardian")).thenReturn(Optional.empty());

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("guardian");
        loginForm.setPassword("password");
        String token = guardianService.guardianLogin(loginForm);

        assertNull(token);
    }

    @Test
    void testGuardianLogin_InvalidPassword() {

        Guardian guardian = new Guardian();
        guardian.setId(1);
        guardian.setUsername("guardian");
        guardian.setPassword("hashedPassword");

        when(guardianRepository.findByUsername("guardian")).thenReturn(Optional.of(guardian));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(false);


        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("guardian");
        loginForm.setPassword("password");
        String token = guardianService.guardianLogin(loginForm);

        assertNull(token);
    }

    @Test
    void testGuardianRegister_ValidParameters() {
        // Arrange
        GuardianRegisterForm form = new GuardianRegisterForm();
        form.setUsername("testUser");
        form.setPassword("testPass");
        form.setEmail("test@example.com");
        form.setFamilyMember("familyMember");

        Guardian guardian = new Guardian();
        guardian.setUsername("testUser");
        guardian.setPassword("encodedPass");
        guardian.setEmail("test@example.com");
        guardian.setFamilyMember("familyMember");
        guardian.setKids(null);

        when(passwordEncoder.encode("testPass")).thenReturn("encodedPass");
        when(guardianRepository.save(Mockito.any(Guardian.class))).thenReturn(guardian);

        // Act & Assert
        Guardian result = guardianService.registerGuardian(form);

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("encodedPass", result.getPassword());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("familyMember", result.getFamilyMember());
        assertNull(result.getKids());
    }

    @Test
    void testGuardianRegister_InvalidInput() {
        // Arrange
        GuardianRegisterForm form = new GuardianRegisterForm();
        form.setUsername(null);
        form.setPassword("testPass");
        form.setEmail("test@example.com");
        form.setFamilyMember("familyMember");

        Guardian guardian = new Guardian();
        guardian.setUsername("testUser");
        guardian.setPassword("encodedPass");
        guardian.setEmail("test@example.com");
        guardian.setFamilyMember("familyMember");
        guardian.setKids(null);

        when(passwordEncoder.encode("testPass")).thenReturn("encodedPass");
        when(guardianRepository.save(Mockito.any(Guardian.class))).thenReturn(guardian);

        // Act & Assert
        Guardian result = guardianService.registerGuardian(form);

        // Assert
        assertNull(result);
    }
}