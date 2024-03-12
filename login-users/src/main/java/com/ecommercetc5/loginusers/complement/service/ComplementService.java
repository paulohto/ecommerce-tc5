package com.ecommercetc5.loginusers.complement.service;

import com.ecommercetc5.loginusers.complement.dto.ComplementDto;
import com.ecommercetc5.loginusers.complement.dto.ComplementUserDto;
import com.ecommercetc5.loginusers.complement.entity.Complement;
import com.ecommercetc5.loginusers.complement.repository.IComplementRepository;
import com.ecommercetc5.loginusers.exception.service.DatabaseException;
import com.ecommercetc5.loginusers.user.entity.User;
import com.ecommercetc5.loginusers.user.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

@Service
public class ComplementService {
    private final IComplementRepository complementRepository;
    private final IUserRepository userRepository;
    public ComplementService(IComplementRepository complementRepository, IUserRepository userRepository) {
        this.complementRepository = complementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ComplementUserDto saveComplement(ComplementUserDto dto){
        try {
            var user = userRepository.getReferenceById(String.valueOf(dto.user().id()) /*dto.user().id()*/);
            var cplEntity = ComplementUserDto.toEntity(dto, user);
            var cplSaved = complementRepository.save(cplEntity);
            return ComplementUserDto.fromEntity(cplSaved);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Usuário não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Page<ComplementUserDto> findAll(PageRequest pageRequest){
        var complements = complementRepository.findAll(pageRequest);
        return complements.map(ComplementUserDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public ComplementUserDto findById(Long id){
        var complement = complementRepository.findById(id).orElseThrow(
                () -> new DatabaseException("Usuário não encontrado com o ID: " + id)
        );
        return ComplementUserDto.fromEntity(complement);
    }

    @Transactional
    public ComplementUserDto updateComplement(Long id, ComplementUserDto dto) {
        try {
            validateComplementDto(dto); // Validar DTO antes da atualização

           // var user = userRepository.getReferenceById( dto.user().id() /*String.valueOf(dto.user().id())*/ /*dto.user().id()*/);
            var user = userRepository.findByLogin(dto.user().login());

            Complement entity = complementRepository.getReferenceById(id);
            ComplementUserDto.mapperDtoToEntity(dto, entity, (User) user);
            entity = complementRepository.save(entity);

            return ComplementUserDto.fromEntity(entity);

        } catch (EntityNotFoundException e) {
            throw new DatabaseException("Complemento não encontrado com o ID: " + id);
        } catch (ValidationException e) {
            throw new DatabaseException("Erro de validação: " + e.getMessage());
        }
    }

    // Método para validar o DTO manualmente
    private void validateComplementDto(ComplementUserDto dto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ComplementUserDto>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<ComplementUserDto> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new ValidationException(errorMessage.toString());
        }
    }

    @Transactional
    public void deleteComplementById(Long id){
        if (complementRepository.existsById(id)) {
            complementRepository.deleteById(id);
        } else {
            throw new DatabaseException("Usuário não encontrado com o ID: " + id);
        }
    }
}
