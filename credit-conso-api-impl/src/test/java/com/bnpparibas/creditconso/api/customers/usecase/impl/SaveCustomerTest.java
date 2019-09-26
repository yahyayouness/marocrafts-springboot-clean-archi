package com.bnpparibas.creditconso.api.customers.usecase.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.bnpparibas.creditconso.api.customers.boundary.ISaveCustomer;
import com.bnpparibas.creditconso.api.customers.request.SaveCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;
import com.bnpparibas.creditconso.domain.entity.Customer;
import com.bnpparibas.creditconso.domain.entity.CustomerFactory;
import com.bnpparibas.creditconso.domain.entitygateway.CustomersEntityGateway;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class SaveCustomerTest {

	private ISaveCustomer saveCustomersUseCase;
	private CustomersEntityGateway customersEntityGateway;

	@Before
	public void initializeComponentUnderTest() {
		customersEntityGateway = mock(CustomersEntityGateway.class);
		saveCustomersUseCase = new SaveCustomerImpl(customersEntityGateway);
	}

	@Test
	public void shouldSaveCustomer() {

		// Given
		SaveCustomerRequest request = new SaveCustomerRequest();
		Customer customer32064 = CustomerFactory.createCustomer(34l,"yahya", "youness", "you@bnpparibas.com",
				"0537352761");

		// When
		when(customersEntityGateway.save(customer32064)).thenReturn(customer32064);
		CustomerResponseModel response = new CustomerResponseModel();
		saveCustomersUseCase.execute(request, response);

		// Then
		assertEquals("yahya", response.getNom());
		assertEquals("youness", response.getPrenom());
		assertEquals("you@bnpparibas.com", response.getEmail());
		assertEquals("0537352761", response.getTelephone());

	}

	@Test
	public void shouldNotSave() {

		// Given
		SaveCustomerRequest request = new SaveCustomerRequest();
		Customer customer32064 = CustomerFactory.createCustomer(65l,"yahya", "youness", "you@bnpparibas.com",
				"0537352761");

		// When
		when(customersEntityGateway.save(customer32064)).thenReturn(customer32064);
		CustomerResponseModel response = new CustomerResponseModel();
		saveCustomersUseCase.execute(request, response);

		// Then
		assertEquals(null, response.getNom());
		assertEquals(null, response.getPrenom());
		assertEquals(null, response.getEmail());
		assertEquals(null, response.getTelephone());

	}

}
