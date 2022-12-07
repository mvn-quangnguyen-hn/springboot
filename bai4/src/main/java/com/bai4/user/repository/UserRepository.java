package com.bai4.user.repository;

import com.bai4.user.model.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);

    @Query("SELECT e FROM User e")
    Page<User> findUsers(Pageable pageable);
}
