package com.example.demo.repo;

import com.example.demo.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepo extends JpaRepository<UserData, Integer> {
    UserData findByUsername(String username);
}
