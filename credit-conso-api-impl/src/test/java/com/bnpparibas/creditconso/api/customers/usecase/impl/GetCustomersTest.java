package com.bnpparibas.creditconso.api.customers.usecase.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.bnpparibas.creditconso.api.customers.boundary.IGetCustomer;
import com.bnpparibas.creditconso.api.customers.request.GetCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;
import com.bnpparibas.creditconso.domain.entity.Customer;
import com.bnpparibas.creditconso.domain.entity.CustomerFactory;
import com.bnpparibas.creditconso.domain.entitygateway.CustomersEntityGateway;

@RunWith(MockitoJUnitRunner.class)
public class GetCustomersTest {

	private IGetCustomer getCustomersUseCase;
	private CustomersEntityGateway customersEntityGateway;

	@Before
	public void initializeComponentUnderTest() {
		customersEntityGateway = mock(CustomersEntityGateway.class);
		getCustomersUseCase = new GetCustomerImpl(customersEntityGateway);
	}

	@Test
	public void shouldFindCustomerById() {
		
		// Given
		GetCustomerRequest request = new GetCustomerRequest(32064L);
		Customer customer32064 = CustomerFactory.createCustomer(233l,"yahya", "youness", "you@bnpparibas.com",
				"0537352761");

		// When
		when(customersEntityGateway.findById(32064l)).thenReturn(customer32064);
		CustomerResponseModel response = new CustomerResponseModel();
		getCustomersUseCase.execute(request, response);

		// Then
		assertEquals("yahya", response.getNom());
		assertEquals("youness", response.getPrenom());
		assertEquals("you@bnpparibas.com", response.getEmail());
		assertEquals("0537352761", response.getTelephone());

	}
	
	@Test
	public void shouldNotFindCustomerById() {
		
		// Given
		GetCustomerRequest request = new GetCustomerRequest(32064L);

		// When
		when(customersEntityGateway.findById(32064l)).thenReturn(null);
		CustomerResponseModel response = new CustomerResponseModel();
		getCustomersUseCase.execute(request, response);

		// Then
		assertEquals(null, response.getNom());
		assertEquals(null, response.getPrenom());
		assertEquals(null, response.getEmail());
		assertEquals(null, response.getTelephone());

	}

}
