package com.example.jdata_01.repository;

import com.example.jdata_01.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends MongoRepository<User, String> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
}
