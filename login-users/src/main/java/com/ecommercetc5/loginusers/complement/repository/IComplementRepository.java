package com.ecommercetc5.loginusers.complement.repository;

import com.ecommercetc5.loginusers.complement.entity.Complement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComplementRepository extends JpaRepository<Complement, Long> {
}
