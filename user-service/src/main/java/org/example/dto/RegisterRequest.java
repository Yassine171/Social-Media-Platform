package org.example.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String username;
    private String password;

    @Nullable
    private String bio;
    @Nullable
    private String profilePicture;
}
