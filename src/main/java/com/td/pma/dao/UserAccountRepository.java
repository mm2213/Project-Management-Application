package com.td.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.td.pma.entities.Project;
import com.td.pma.entities.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{
	
	@Override
	public List<UserAccount> findAll();
	
}
