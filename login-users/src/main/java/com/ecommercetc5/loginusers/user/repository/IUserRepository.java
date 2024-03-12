package com.ecommercetc5.loginusers.user.repository;

import com.ecommercetc5.loginusers.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

//@Repository
//public interface IUserRepository extends JpaRepository<User, Long> {
//
//}

//@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}