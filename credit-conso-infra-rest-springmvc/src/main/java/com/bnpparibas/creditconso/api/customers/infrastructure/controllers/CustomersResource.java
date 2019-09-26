package com.bnpparibas.creditconso.api.customers.infrastructure.controllers;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bnpparibas.creditconso.api.customers.boundary.IGetCustomer;
import com.bnpparibas.creditconso.api.customers.boundary.ISaveCustomer;
import com.bnpparibas.creditconso.api.customers.infrastructure.exceptions.customers.CustomersNotFoundException;
import com.bnpparibas.creditconso.api.customers.request.GetCustomerRequest;
import com.bnpparibas.creditconso.api.customers.request.SaveCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@Api(value = "account/v1/customers-resource", description = "<p> * Customers API gives developers and third application"
		+ " a way to create and retrieve informations about customers (the customer information can be provided from social identity provided like google"
		+ " and facebook and soon the bnpparibas identity provider.<br> " + "<br>* With Customers API, you'll find"
		+ "\r\n" + "<br>- The user's full name<br>" + "- Date of birth<br>" + "- Sex<br>" + "- Email<br>"
		+ "- Phone number<br>"
		+ "<br>* For information this is temporary because all the federation of identity will be delegated to the open id connect."
		+ "<br><br>- This API is available in the following environments:<br> " + "<br>- URI Sandbox (https://..)" + ""
		+ "<br>- URI Live (production)</p>")
public class CustomersResource {

	private  final Logger LOGGER = LoggerFactory.getLogger(CustomersResource.class);

	private ISaveCustomer saveCustomer;

	private IGetCustomer getCustomer;

	@Autowired
	public CustomersResource(ISaveCustomer saveCustomers, IGetCustomer getCustomers) {
		super();
		this.saveCustomer = saveCustomers;
		this.getCustomer = getCustomers;
	}

	@ApiOperation(value = "Save new customer", tags = "customers-resource")
	@RequestMapping(value = "/v1/customers", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<CustomerResponseModel> saveCustomer(
			@RequestBody SaveCustomerRequest dtoCustomer) {
		LOGGER.info("Call saveCustomer with params: [data customer]={}.", dtoCustomer);

		CustomerResponseModel result = new CustomerResponseModel();
		saveCustomer.execute(dtoCustomer, result);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand((null != result.getId()) ? result.getId() : "empty").toUri();

		LOGGER.info("Return call saveCustomer with result: [uri]={}.", location.getPath());
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Get customer informations by client identifier", response = CustomerResponseModel.class, tags = "customers-resource")
	@RequestMapping(value = "/v1/customers/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomerResponseModel> getCustomerById(@PathVariable(required = true) Long id) {

		LOGGER.info("Call getCustomerById with param: [id]={}.", id);
		final CustomerResponseModel client = new CustomerResponseModel();

		getCustomer.execute(new GetCustomerRequest(id), client);

		if (null == client.getId()) {
			throw new CustomersNotFoundException("id-" + id);
		}

		LOGGER.debug("Response getCustomerById with param: [id]={}.", client);
		return ResponseEntity.ok().body(client);
	}

}
