package com.spring.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUserNameAndActiveTrue(String userName);

	User findByUserName(String userName);

}