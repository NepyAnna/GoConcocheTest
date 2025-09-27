package com.more_than_code.go_con_coche.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.more_than_code.go_con_coche.auth.dtos.AuthRequest;
import com.more_than_code.go_con_coche.auth.dtos.RegisterRequest;
import com.more_than_code.go_con_coche.auth.services.JwtService;
import com.more_than_code.go_con_coche.registered_user.RegisteredUser;
import com.more_than_code.go_con_coche.registered_user.RegisteredUserRepository;
import com.more_than_code.go_con_coche.role.Role;
import com.more_than_code.go_con_coche.role.RoleRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private RegisteredUserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        // Mock role for the test
        Role role = Role.builder().id(2L).role("RENTER").build();
        when(roleRepository.findById(2L)).thenReturn(Optional.of(role));
        
        // Mock user repository to return a saved user
        RegisteredUser savedUser = RegisteredUser.builder()
                .id(1L)
                .username("testuser")
                .roles(Set.of(role))
                .build();
        when(userRepository.save(any(RegisteredUser.class))).thenReturn(savedUser);
    }

    @Test
    void registerUser_ShouldReturn201() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("Tom",
                "Black",
                LocalDate.of(1999, 2, 19),
                "+34695689863" ,
                "testuser",
                "test@mail.com" ,
                "Password123!", Set.of(2L));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void refreshToken_ShouldReturn200() throws Exception {
        String refreshToken = jwtService.generateRefreshToken("owner");

        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);

        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(refreshCookie))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
    }
}