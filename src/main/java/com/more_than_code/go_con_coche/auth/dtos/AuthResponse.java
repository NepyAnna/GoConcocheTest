package com.more_than_code.go_con_coche.auth.dtos;
import com.more_than_code.go_con_coche.role.Role;
import java.util.Set;

public record AuthResponse(String username,
                           String token,
                           Set<String> roles) {
}
