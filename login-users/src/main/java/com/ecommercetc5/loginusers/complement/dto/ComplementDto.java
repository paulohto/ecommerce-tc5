package com.ecommercetc5.loginusers.complement.dto;

import com.ecommercetc5.loginusers.complement.entity.Complement;
import com.ecommercetc5.loginusers.exception.service.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ComplementDto(

        Long id,

        @NotBlank(message = "O Nome não pode estar em branco")
        String name,

        @NotBlank(message = "O Telefone não pode estar em branco")
        @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}", message = "O telefone deve estar no formato (DD) 00000-0000")
        String phone,

        @ValidDate(message = "A data não pode estar em branco. A data deve estar no formato YYYY-MM-DD")
        LocalDate birthday,
        @NotBlank(message = "O CPF não pode estar em branco")
        @CPF(message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O CEP não pode estar em branco")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ertar no formato 12345-678")
        String cep,

        @NotBlank(message = "A Rua/Via/Avenida não pode estar em branco")
        String rua,
        Integer numero,
        String complemento,

        @NotBlank(message = "O Bairro não pode estar em branco")
        String bairro,
        @NotBlank(message = "A Cidade não pode estar em branco")
        String cidade,

        @NotBlank(message = "O estado não pode estar em branco")
        @Size(min = 2, max =2 , message = "O estado deve ter exatamente 2 caracteres")
        String uf
) {
        public ComplementDto(Complement complement){
                this(   complement.getId(),
                        complement.getName(),
                        complement.getPhone(),
                        complement.getBirthday(),
                        complement.getCpf(),

                        complement.getCep(),
                        complement.getRua(),
                        complement.getNumero(),
                        complement.getComplemento(),
                        complement.getBairro(),
                        complement.getCidade(),
                        complement.getUf()
                );}

        public static ComplementDto fromEntity(Complement entity){
                return new ComplementDto(
                        entity.getId(),
                        entity.getName(),
                        entity.getPhone(),
                        entity.getBirthday(),
                        entity.getCpf(),

                        entity.getCep(),
                        entity.getRua(),
                        entity.getNumero(),
                        entity.getComplemento(),
                        entity.getBairro(),
                        entity.getCidade(),
                        entity.getUf()
                );
        }

        public static Complement toEntity(ComplementDto dto){
                return new Complement(
                        dto.id,
                        dto.name,
                        dto.phone,
                        dto.birthday,
                        dto.cpf,

                        dto.cep,
                        dto.rua,
                        dto.numero,
                        dto.complemento,
                        dto.bairro,
                        dto.cidade,
                        dto.uf
                );
        }

        public static Complement mapperDtoToEntity(ComplementDto dto, Complement entity){
                //entity.setId(dto.id);
                entity.setName(dto.name);
                entity.setPhone(dto.phone);
                entity.setBirthday(dto.birthday);
                entity.setCpf(dto.cpf);

                entity.setCep(dto.cep);
                entity.setRua(dto.rua);
                entity.setNumero(dto.numero);
                entity.setComplemento(dto.complemento);
                entity.setBairro(dto.bairro);
                entity.setCidade(dto.cidade);
                entity.setUf(dto.uf);

                return entity;
        }

}
