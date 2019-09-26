package com.bnpparibas.creditconso.domain.entity;

public class CustomerFactory {

	private CustomerFactory() {
	}

	public static CustomerFactory create() {
		return new CustomerFactory();
	}

	public static Customer createCustomer(long id, String nom, String prenom, String email, String telephone) {
		return new Customer(id,nom, prenom, email, telephone);
	}

}
