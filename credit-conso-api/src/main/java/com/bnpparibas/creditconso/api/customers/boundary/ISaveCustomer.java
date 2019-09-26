package com.bnpparibas.creditconso.api.customers.boundary;

import com.bnpparibas.creditconso.api.customers.request.SaveCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;

@FunctionalInterface
public interface ISaveCustomer {
	void execute(SaveCustomerRequest request, CustomerResponseModel response);
}