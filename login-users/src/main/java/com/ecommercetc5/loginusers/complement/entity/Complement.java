package com.ecommercetc5.loginusers.complement.entity;

import com.ecommercetc5.loginusers.complement.dto.ComplementDto;
import com.ecommercetc5.loginusers.complement.dto.ComplementUserDto;
import com.ecommercetc5.loginusers.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_complement")
public class Complement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private LocalDate birthday;
    private String cpf;

    private String cep;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Complement(){}

    public Complement(
            Long id,
            String name,
            String phone,
            LocalDate birthday,
            String cpf,

            String cep,
            String rua,
            Integer numero,
            String complemento,
            String bairro,
            String cidade,
            String uf
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.cpf = cpf;

        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Complement (ComplementDto dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.phone = dto.phone();
        this.birthday = dto.birthday();
        this.cpf = dto.cpf();

        this.cep = dto.cep();
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
    }

    public Complement (ComplementUserDto dto, User user) {
        this.id = dto.id();
        this.name = dto.name();
        this.phone = dto.phone();
        this.birthday = dto.birthday();
        this.cpf = dto.cpf();

        this.cep = dto.cep();
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.user = user;
    }

}
