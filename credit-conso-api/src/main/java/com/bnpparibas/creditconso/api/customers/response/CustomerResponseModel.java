package com.bnpparibas.creditconso.api.customers.response;

/**
 * POJO output response for Response model.
 */
public class CustomerResponseModel {
	
	private Long id;
	private String sub;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	
	

	public CustomerResponseModel(String nom, String prenom, String email, String telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
	}
	
	public CustomerResponseModel() {
	}
	
	

	public CustomerResponseModel(Long id, String sub, String nom, String prenom, String email, String telephone) {
		super();
		this.id = id;
		this.sub = sub;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "CustomersResponseModel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}


}
