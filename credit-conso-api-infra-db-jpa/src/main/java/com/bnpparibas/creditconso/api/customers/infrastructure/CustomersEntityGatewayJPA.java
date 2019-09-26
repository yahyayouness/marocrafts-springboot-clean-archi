package com.bnpparibas.creditconso.api.customers.infrastructure;

import javax.inject.Named;

import com.bnpparibas.creditconso.api.customers.infrastructure.db.CustomerEntity;
import com.bnpparibas.creditconso.api.customers.infrastructure.repository.CustomerRepository;
import com.bnpparibas.creditconso.domain.entity.Customer;
import com.bnpparibas.creditconso.domain.entity.CustomerFactory;
import com.bnpparibas.creditconso.domain.entitygateway.CustomersEntityGateway;

@Named
public class CustomersEntityGatewayJPA implements CustomersEntityGateway {

	private final CustomerRepository customerRepository = null;

	public Customer findByName(String name) {
		return this.toDomain(customerRepository.findByName(name));
	}

	private Customer toDomain(CustomerEntity entity) {
		Customer customer = CustomerFactory.createCustomer(entity.getId(),entity.getNom(), entity.getPrenom(), entity.getEmail(),
				entity.getTelephone());
		return customer;
	}

	public Customer findById(long id) {
		// CustomerEntity entity =
		// customerRepository.findById(String.valueOf(id)).get();
		return CustomerFactory.createCustomer(143l, "yahya spring mvc", "youness", "you@octo.com", "0619714789");

	}

	public Customer save(Customer customer) {
		return CustomerFactory.createCustomer(143l, "yahya", "youness", "you@octo.com", "0619714789");
	}

}
