package com.ecommercetc5.loginusers.user.dto;

import com.ecommercetc5.loginusers.user.entity.User;
import com.ecommercetc5.loginusers.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto (
    Long id,

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(regexp = ".+@.+\\..+", message = "e-mail inválido")
    String login,

    @NotBlank(message = "O password não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter pelo menos {min} caracteres")
    String password,

    UserRole role

) {
    public UserDto(User user) {
        this(user.getId(), user.getLogin(), user.getPassword(), user.getRole());
    }
    public static UserDto fromEntity(User entity){
        return new UserDto(
                entity.getId(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getRole()
        );
    }
    public static User toEntity(UserDto dto){
        return new User(
                dto.id,
                dto.login,
                dto.password,
                dto.role
        );
    }
    public static User mapperDtoToEntity(UserDto dto, User entity){
        //entity.setId(dto.id);
        entity.setLogin(dto.login);
        entity.setPassword(dto.password);
        entity.setRole(dto.role);
        return entity;
    }

}
