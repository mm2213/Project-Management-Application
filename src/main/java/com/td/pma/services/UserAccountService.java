package com.td.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.pma.dao.UserAccountRepository;
import com.td.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository usrAccRepo;

	public List<UserAccount> getAll() {
		return usrAccRepo.findAll();
	}

	public void save(UserAccount user) {
		usrAccRepo.save(user);
	}
	
}
