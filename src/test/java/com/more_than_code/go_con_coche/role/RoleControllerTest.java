package com.more_than_code.go_con_coche.role;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.more_than_code.go_con_coche.auth.dtos.AuthRequest;
import com.more_than_code.go_con_coche.registered_user.RegisteredUser;
import com.more_than_code.go_con_coche.registered_user.RegisteredUserRepository;
import com.more_than_code.go_con_coche.registered_user.dtos.JwtResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Integration tests for RoleController using JWT")
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private String jwt;

    @BeforeEach
    void setup() throws Exception {
        Role adminRole = roleRepository.findByRoleIgnoreCase("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN", null)));

        String ADMIN_REGISTEREDUSERNAME = "testAdminUser";
        String ADMIN_EMAIL = "testadmin@example.com";
        String ADMIN_PASSWORD = "Testadminpassword!";

        Optional<RegisteredUser> existingAdmin = registeredUserRepository.findByUsername(ADMIN_REGISTEREDUSERNAME);

        if (existingAdmin.isEmpty()) {
            RegisteredUser adminRegisteredUserEntity = RegisteredUser.builder()
                    .username(ADMIN_REGISTEREDUSERNAME)
                    .email(ADMIN_EMAIL)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .roles(Set.of(adminRole))
                    .build();
            registeredUserRepository.save(adminRegisteredUserEntity);
            }

        AuthRequest adminLoginRequest = new AuthRequest(ADMIN_REGISTEREDUSERNAME, ADMIN_PASSWORD);

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminLoginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        JwtResponse jwtResponse = objectMapper.readValue(
                loginResult.getResponse().getContentAsString(),
                JwtResponse.class
        );
            this.jwt = jwtResponse.token();
        }

}
