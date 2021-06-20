package com.novi.hexagon.repository;

import com.novi.hexagon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
