package com.ecommercetc5.loginusers.user.dto;

import com.ecommercetc5.loginusers.complement.dto.ComplementDto;
import com.ecommercetc5.loginusers.user.entity.User;
import com.ecommercetc5.loginusers.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserComplementDto (
        Long id,

        @NotBlank(message = "O nome de usuário não pode estar em branco")
        String login,

        @NotBlank(message = "O password não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos {min} caracteres")
        String password,

        @NotBlank(message = "A Role deve ser tipo: USER ou ADMIN")
        @Pattern(regexp = "^(USER|ADMIN)$", message = "A Role deve ser tipo: USER ou ADMIN")
        UserRole role,

//        @NotBlank(message = "O e-mail não pode estar em branco")
//        @Email(regexp = ".+@.+\\..+", message = "e-mail inválido")
//        String email,

        ComplementDto complement

) {
        public  static UserComplementDto fromEntity(User user) {
                return new UserComplementDto(
                        user.getId(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getRole(),
                        //user.getEmail(),
                        user.getComplement() != null ? new ComplementDto(user.getComplement()) : null
                );
        }
}
