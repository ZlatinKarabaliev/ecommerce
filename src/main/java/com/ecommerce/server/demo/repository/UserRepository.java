package com.ecommerce.server.demo.repository;

import com.ecommerce.server.demo.model.trg.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
