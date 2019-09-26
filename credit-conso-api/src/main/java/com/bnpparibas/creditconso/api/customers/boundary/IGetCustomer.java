package com.bnpparibas.creditconso.api.customers.boundary;

import com.bnpparibas.creditconso.api.customers.request.GetCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;

@FunctionalInterface
public interface IGetCustomer {
	void execute(GetCustomerRequest request, CustomerResponseModel response);
}