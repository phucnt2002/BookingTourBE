package com.vn.tour.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tour.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	public Account findByUserNameAndPassword(String userName, String password);
	public Optional<Account> findByUserName(String userName);
}
