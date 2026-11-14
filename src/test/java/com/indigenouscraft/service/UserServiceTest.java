
package com.indigenouscraft.service;

import com.indigenouscraft.entity.User;
import com.indigenouscraft.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Ahmet");
        user.setSurname("Gulsoy");
        user.setEmail("ahmet@test.com");
        user.setPassword("password123");
    }

    @Test
    void registerUser_shouldSaveUser_whenEmailIsNew() {

        // Arrange
        when(userRepository.existsByEmail("ahmet@test.com"))
                .thenReturn(false);

        when(passwordEncoder.encode("password123"))
                .thenReturn("hashedPassword");

        when(userRepository.saveAndFlush(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User savedUser = userService.registerUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("ahmet@test.com", savedUser.getEmail());
        assertEquals("hashedPassword", savedUser.getPassword());

        verify(userRepository).existsByEmail("ahmet@test.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).saveAndFlush(user);
    }

    @Test
    void registerUser_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        when(userRepository.existsByEmail("ahmet@test.com"))
                .thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.registerUser(user)
        );

        assertEquals(
                "This email address is already registered.",
                exception.getMessage()
        );

        // Verify
        verify(userRepository).existsByEmail("ahmet@test.com");

        verify(passwordEncoder, never()).encode(anyString());

        verify(userRepository, never()).saveAndFlush(any(User.class));
    }
    @Test
    void registerUser_shouldNormalizeEmail_whenEmailContainsSpacesOrUppercase() {
        // Arrange
        user.setEmail("  Ahmet@Test.com  ");

        when(userRepository.existsByEmail("ahmet@test.com"))
                .thenReturn(false);

        when(passwordEncoder.encode("password123"))
                .thenReturn("hashedPassword");

        when(userRepository.saveAndFlush(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User savedUser = userService.registerUser(user);

        // Assert
        assertEquals("ahmet@test.com", savedUser.getEmail());

        verify(userRepository).existsByEmail("ahmet@test.com");
        verify(userRepository).saveAndFlush(user);
    }
}
