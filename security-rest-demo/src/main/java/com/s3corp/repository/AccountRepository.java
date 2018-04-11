package com.s3corp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.s3corp.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	Account findByUserNameAndActiveTrue(String userName);

}