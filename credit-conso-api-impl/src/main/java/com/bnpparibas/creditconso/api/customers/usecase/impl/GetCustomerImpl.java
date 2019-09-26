package com.bnpparibas.creditconso.api.customers.usecase.impl;

import javax.inject.Named;

import com.bnpparibas.creditconso.api.customers.boundary.IGetCustomer;
import com.bnpparibas.creditconso.api.customers.request.GetCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;
import com.bnpparibas.creditconso.domain.entity.Customer;
import com.bnpparibas.creditconso.domain.entitygateway.CustomersEntityGateway;

@Named
public class GetCustomerImpl implements IGetCustomer {

	private CustomersEntityGateway customerEntityGateway;

	public GetCustomerImpl(CustomersEntityGateway buildingEntityGateway) {
		this.customerEntityGateway = buildingEntityGateway;
	}

	public void execute(GetCustomerRequest request, CustomerResponseModel response) {

		final Customer customer = customerEntityGateway.findById(request.getId());

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
