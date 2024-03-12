package com.ecommercetc5.loginusers.user.service;

import com.ecommercetc5.loginusers.user.dto.UserComplementDto;
import com.ecommercetc5.loginusers.user.dto.UserDto;
import com.ecommercetc5.loginusers.user.entity.User;
import com.ecommercetc5.loginusers.exception.service.ControllerNotFoundException;
import com.ecommercetc5.loginusers.exception.service.DatabaseException;
import com.ecommercetc5.loginusers.user.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto saveUser(UserDto userDto){
        var userEntity = UserDto.toEntity(userDto);
        var userSaved = userRepository.save(userEntity);
        return UserDto.fromEntity(userSaved);
    }

    @Transactional(readOnly = true)
    public Page<UserComplementDto> findAll(PageRequest pageRequest){
        //PageRequest pageRequest = PageRequest.of(page, size);
        var users = userRepository.findAll(pageRequest);
        return users.map(UserComplementDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public UserComplementDto findById(Long id){
        var user = userRepository.findById( String.valueOf(id) ).orElseThrow(
                () -> new DatabaseException("Usuário não encontrado com o ID: " + id)
        );
        return UserComplementDto.fromEntity(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDto){
        try {
            User userEntity =userRepository.getReferenceById( String.valueOf(id) );
            UserDto.mapperDtoToEntity(userDto, userEntity);
            userEntity = userRepository.save(userEntity);

            return UserDto.fromEntity(userEntity);

        } catch (EntityNotFoundException e){
            throw new DatabaseException("Usuário não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deleteUserById(Long id){
        if (userRepository.existsById( String.valueOf(id) )) {
            userRepository.deleteById( String.valueOf(id) );
        } else {
            throw new DatabaseException("Usuário não encontrado com o ID: " + id);
        }
    }

}
