package com.bnpparibas.creditconso.domain.entitygateway;

import com.bnpparibas.creditconso.domain.entity.Customer;

public interface CustomersEntityGateway {

	Customer findByName(String name);

	Customer findById(long id);
	
	Customer save(Customer customer);

}
