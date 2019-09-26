package com.bnpparibas.creditconso.api.customers.request;

/**
 * POJO input request to get customers
 */
public class GetCustomerRequest {

	private Long id;
	private String nom;
	private String prenom;

	public GetCustomerRequest(Long id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public GetCustomerRequest(Long id) {
		super();
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "GetCustomersRequest [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
}
