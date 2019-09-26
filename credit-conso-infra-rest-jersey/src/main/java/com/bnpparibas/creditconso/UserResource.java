package com.bnpparibas.creditconso;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bnpparibas.creditconso.api.customers.boundary.IGetCustomer;
import com.bnpparibas.creditconso.api.customers.request.GetCustomerRequest;
import com.bnpparibas.creditconso.api.customers.response.CustomerResponseModel;


@Path("/v1/customers")
public class UserResource {
	
	@Autowired
	private IGetCustomer getCustomer;

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getUserById(@PathParam("id") Long id) throws URISyntaxException {
		
		final CustomerResponseModel client = new CustomerResponseModel();

		getCustomer.execute(new GetCustomerRequest(id), client);

		return Response.status(200).entity(
				client)
				.contentLocation(new URI("/v1/customers/" + id)).build();
	}

}
