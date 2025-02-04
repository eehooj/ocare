package com.example.ocare.api.user.domain.repository;

import com.example.ocare.api.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
