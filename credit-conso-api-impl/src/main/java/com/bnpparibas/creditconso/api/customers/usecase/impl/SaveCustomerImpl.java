package com.bnpparibas.creditconso.api.customers.usecase.impl;

import javax.inject.Named;

import com.bnpparibas.creditconso.api.customers.boundary.ISaveCustomer;
import com.bnpparibas.creditconso.api.customers.request.SaveCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;
import com.bnpparibas.creditconso.domain.entity.Customer;
import com.bnpparibas.creditconso.domain.entitygateway.CustomersEntityGateway;

@Named
public class SaveCustomerImpl implements ISaveCustomer {

	private CustomersEntityGateway customerEntityGateway;

	public SaveCustomerImpl(CustomersEntityGateway buildingEntityGateway) {
		this.customerEntityGateway = buildingEntityGateway;
	}

	public void execute(SaveCustomerRequest request, CustomerResponseModel response) {

		Customer requestCustomer = null;
		final Customer customer = customerEntityGateway.save(requestCustomer);

		presenterCustomer(response, customer);
	}

	private void presenterCustomer(CustomerResponseModel response, final Customer customer) {
		if (null != customer) {
			response.setId(customer.getId());
			response.setNom(customer.getNom());
			response.setPrenom(customer.getPrenom());
			response.setEmail(customer.getEmail());
			response.setTelephone(customer.getTelephone());
		}
	}
}
