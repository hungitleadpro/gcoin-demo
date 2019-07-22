package com.feature.gcoin.repository;


import com.feature.gcoin.dto.request.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.feature.gcoin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User p WHERE LOWER(p.password) = LOWER(:password) AND LOWER(p.username) = LOWER(:username)")
    public List<User> login(@Param("username") String username, @Param("password") String password);

    User findByUsername(String userName);

    List<User> findByAddress(String address);

    User findById(Long userId);

    @Query("FROM User p WHERE isFeature = 1")
    List<User> findByIsFeature();
}

