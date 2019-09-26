package com.bnpparibas.creditconso.api.customers.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bnpparibas.creditconso.api.customers.infrastructure.db.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

	public CustomerEntity findByName(String name);
}
